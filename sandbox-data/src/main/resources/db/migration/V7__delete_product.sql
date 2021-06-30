alter table event_product
    drop foreign key FK_event_product_event_id,
    drop foreign key FK_event_product_product_id;

drop table event_product;

alter table product
    drop foreign key FK_product_category_id,
    drop foreign key FK_product_vendor_id;

drop table product;

alter table event
    add category_id bigint,
    add constraint FK_event_category_id foreign key (category_id)
        references category (id);

alter table event
    add vendor_id bigint,
    add constraint FK_event_vendor_id foreign key (vendor_id)
        references vendor (id);

alter table user_order
    add date_event date;