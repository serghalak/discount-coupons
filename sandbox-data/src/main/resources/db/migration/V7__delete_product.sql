alter table event_product
    drop foreign key FK4ad8tyf4bpqr6h821jh42uqxl,
    drop foreign key FKntuxn8awf9tn7rxvximxbhb2x;

drop table event_product;

alter table product
    drop foreign key FK1mtsbur82frn64de7balymq9s,
    drop foreign key FK9tnjxr4w1dcvbo2qejikpxpfy;

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