package com.exadel.sandbox.impl;

import com.exadel.sandbox.dto.request.location.VendorLocationRequest;
import com.exadel.sandbox.dto.request.location.VendorLocationUpdateRequest;
import com.exadel.sandbox.dto.request.vendor.VendorRequest;
import com.exadel.sandbox.dto.request.vendor.VendorUpdateRequest;
import com.exadel.sandbox.dto.response.city.CityResponse;
import com.exadel.sandbox.dto.response.location.LocationResponse;
import com.exadel.sandbox.dto.response.vendor.VendorDetailsResponse;
import com.exadel.sandbox.mappers.vendor.VendorMapper;
import com.exadel.sandbox.mappers.vendor.VendorShortMapper;
import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.location.Country;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.CityService;
import com.exadel.sandbox.service.LocationService;
import com.exadel.sandbox.service.exceptions.DuplicateNameException;
import com.exadel.sandbox.service.impl.VendorDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VendorDetailsServiceImplTest {
    @Mock
    private VendorRepository repository;
    @Mock
    private VendorMapper vendorMapper;
    @Mock
    private VendorShortMapper vendorShortMapper;
    @Mock
    private CityService cityService;
    @Mock
    private LocationService locationService;

    @InjectMocks
    private VendorDetailsServiceImpl vendorService;

    @Test
    void shouldFindVendorByIdWhenVendorIsPresent() {
        var id = 1L;
        var vendor = getVendor();
        var vendorDetailsResponse = getVendorDetailsResponse();

        when(repository.findByIdFetchLocations(anyLong())).thenReturn(Optional.of(vendor));
        when(vendorMapper.vendorToVendorDetailsResponse(vendor)).thenReturn(vendorDetailsResponse);

        var existedVendor = vendorService.findByIdWithLocations(id);
        assertThat(existedVendor).isEqualTo(vendorDetailsResponse);
    }

    @Test
    void shouldCreateNewVendor() {
        var vendor = getVendor();
        var vendorRequest = getVendorRequest();
        var vendorDetailsResponse = getVendorDetailsResponse();

        when(vendorMapper.vendorRequestToVendor(vendorRequest)).thenReturn(vendor);
        when(vendorMapper.vendorToVendorDetailsResponse(vendor)).thenReturn(vendorDetailsResponse);
        when(repository.save(any())).then(a -> a.getArgument(0));

        var savedVendor = vendorService.create(vendorRequest);
        assertThat(savedVendor).isEqualTo(vendorDetailsResponse);
    }

    @Test
    void shouldThrowEntityNotFundExceptionWhenVendorIsNotPresent() {
        var vendorId = 1L;

        when(repository.findByIdFetchLocations(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> vendorService.findByIdWithLocations(vendorId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage(String.format("Vendor with id %d not found", vendorId));
    }

    @Test
    void shouldThrowDuplicateNameExceptionWhenVendorNameAlreadyExists() {
        var vendorRequest = VendorRequest.builder()
                .name("puma")
                .build();

        when(repository.findByName(anyString())).thenReturn(Optional.of(new Vendor()));

        assertThatThrownBy(
                () -> vendorService.create(vendorRequest))
                .isInstanceOf(DuplicateNameException.class)
                .hasMessage("Vendor already exists");
    }

    @Test
    void shouldDeleteVendorIfEventsDontExist(){
        when(repository.drop(anyLong())).thenReturn(true);

        assertTrue(vendorService.remove(1L));
    }

    @Test
    void shouldNotDeleteVendorIfEventsExist(){
        when(repository.drop(anyLong())).thenReturn(false);

        assertFalse(vendorService.remove(1L));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionIfVendorIsNotPresent(){
        var vendorUpdateRequest = getVendorUpdateRequest();
        var vendorId = 1L;

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(
                () -> vendorService.update(vendorId, vendorUpdateRequest))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage(String.format("Not found vendor by id %d", vendorId));
    }

    @Test
    void shouldUpdateVendor(){
        var vendorUpdateRequest = getVendorUpdateRequest();
        var vendorId = 1L;
        var vendor = getVendor();
        var vendorDetailsResponse = getVendorDetailsResponse();

        when(repository.findById(vendorId)).thenReturn(Optional.of(vendor));
        when(repository.save(any())).then(a -> a.getArgument(0));
        when(vendorMapper.vendorUpdateRequestToVendor(vendorUpdateRequest)).thenReturn(vendor);
        when(vendorMapper.vendorToVendorDetailsResponse(vendor)).thenReturn(vendorDetailsResponse);

        var updatedVendor = vendorService.update(vendorId, vendorUpdateRequest);

        assertThat(updatedVendor).isEqualTo(vendorDetailsResponse);
    }

    private Set<Location> getLocations() {
        return Stream.of(Location.builder()
                .latitude(10)
                .longitude(10)
                .city(
                        City.builder()
                                .name("Lviv")
                                .country(
                                        Country.builder()
                                                .name("Ukraine")
                                                .build())
                                .build())
                .number("23")
                .street("Rivna")
                .build())
                .collect(Collectors.toSet());
    }

    private Set<LocationResponse> getLocationResponses() {
        return Stream.of(
                LocationResponse.builder()
                        .latitude(10)
                        .longitude(10)
                        .city(
                                CityResponse.builder()
                                        .name("Lviv")
                                        .countryName("Ukraine")
                                        .build())
                        .number("23")
                        .street("Rivna")
                        .build())
                .collect(Collectors.toSet());
    }

    private Vendor getVendor() {
        var vendor = Vendor.builder()
                .name("Noa")
                .description("Tasty food. Delicious drinks")
                .email("noa@gmail.com")
                .phoneNumber("0936477859")
                .locations(getLocations())
                .build();
        vendor.setId(1L);
        return vendor;
    }

    private VendorDetailsResponse getVendorDetailsResponse() {
        return VendorDetailsResponse.builder()
                .name("Noa")
                .description("Tasty food. Delicious drinks")
                .email("noa@gmail.com")
                .phoneNumber("0936477859")
                .id(1L)
                .locationResponses(getLocationResponses())
                .build();
    }

    private Set<VendorLocationRequest> getVendorLocationsRequests() {
        return Stream.of(
                VendorLocationRequest.builder()
                        .latitude(10)
                        .longitude(10)
                        .number("11")
                        .street("Rivna")
                        .cityId(1L)
                        .build())
                .collect(Collectors.toSet());
    }

    private VendorRequest getVendorRequest() {
        return VendorRequest.builder()
                .description("test test test test")
                .email("noa@gmail.com")
                .name("noa")
                .phoneNumber("0937488394")
                .locationRequests(getVendorLocationsRequests())
                .build();
    }

    private Set<VendorLocationUpdateRequest> getVendorLocationUpdateRequests(){
        return Stream.of(
                VendorLocationUpdateRequest.builder()
                        .latitude(10)
                        .longitude(10)
                        .number("11")
                        .street("Rivna")
                        .cityId(1L)
                        .build())
                .collect(Collectors.toSet());
    }

    private VendorUpdateRequest getVendorUpdateRequest(){
        return VendorUpdateRequest.builder()
                .description("test test test test")
                .email("noa@gmail.com")
                .name("noa")
                .phoneNumber("0937488394")
                .locationRequests(getVendorLocationUpdateRequests())
                .build();
    }
}