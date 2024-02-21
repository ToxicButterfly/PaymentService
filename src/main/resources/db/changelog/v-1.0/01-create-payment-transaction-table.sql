create table payment_transaction (
                                     amount float4,
                                     driver_id integer,
                                     passenger_id integer,
                                     ride_id integer,
                                     successful boolean not null,
                                     transaction_id serial not null,
                                     primary key (transaction_id)
)