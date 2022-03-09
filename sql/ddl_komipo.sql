create table c_area
(
    areakey     integer not null,
    areaid      varchar(255),
    description varchar(255)
);

alter table c_area
    owner to k1pledev;

create table c_equipment
(
    areakey      integer not null,
    equipmentkey integer not null,
    equipmentid  varchar(255),
    description  varchar(255),
    referencerpm double precision
);

alter table c_equipment
    owner to k1pledev;

create table c_component
(
    areakey       integer not null,
    equipmentkey  integer not null,
    componentkey  integer not null,
    componentid   varchar(255),
    description   varchar(255),
    speedloadtype smallint,
    referencerpm  double precision,
    comptype      integer,
    orientation   integer,
    mount         integer,
    componentinfo varchar(65535)
);

alter table c_component
    owner to k1pledev;

create table c_measpt
(
    areakey         integer not null,
    equipmentkey    integer not null,
    componentkey    integer not null,
    mptkey          integer not null,
    mptid           varchar(255),
    description     varchar(255),
    companionmptkey integer,
    referencerpm    double precision,
    signaltype      integer,
    orientation     integer,
    position        integer,
    activealarm     boolean,
    alarmtype       integer,
    alerthigh       double precision,
    faulthigh       double precision,
    alertlow        double precision,
    faultlow        double precision
);

alter table c_measpt
    owner to k1pledev;

create table c_measparam
(
    mptkey      integer not null,
    paramidx    integer not null,
    paramid     varchar(255),
    description varchar(255),
    paramtype   integer,
    units       integer,
    unitstr     varchar(10),
    lowerband   real,
    upperband   real,
    activealarm boolean,
    alarmtype   integer,
    alerthigh   double precision,
    faulthigh   double precision,
    alertlow    double precision,
    faultlow    double precision
);

alter table c_measparam
    owner to k1pledev;

create table m_datasource
(
    dskey          integer not null,
    dsname         varchar(255),
    dstype         integer,
    conversiontype integer,
    connectioninfo varchar(65535)
);

alter table m_datasource
    owner to k1pledev;

create table m_mapping
(
    dskey    integer not null,
    mptkey   integer not null,
    synctwf  varchar(255),
    syncfft  varchar(255),
    asynctwf varchar(255),
    overall  varchar(255),
    param1   varchar(255),
    param2   varchar(255),
    param3   varchar(255),
    param4   varchar(255),
    param5   varchar(255),
    param6   varchar(255),
    param7   varchar(255),
    param8   varchar(255)
);

alter table m_mapping
    owner to k1pledev;

create table d_spectrum
(
    mptkey      integer   not null,
    measdt      timestamp not null,
    spctype     integer   not null,
    storedt     timestamp,
    fmax        integer,
    windowfunc  integer,
    deltafreq   double precision,
    deltaorder  double precision,
    rpm         double precision,
    units       double precision,
    unitstr     varchar(10),
    rawdata     bytea,
    overall     double precision,
    waveformkey bigint
);

alter table d_spectrum
    owner to k1pledev;

create table d_parametertrend
(
    mptkey       integer   not null,
    parmidx      integer   not null,
    measdt       timestamp not null,
    paramtype    integer,
    storedt      timestamp,
    units        double precision,
    unitstr      varchar(10),
    value        double precision,
    alarm_status integer,
    waveformkey  bigint
);

alter table d_parametertrend
    owner to k1pledev;

create table x_a_vib_parameters
(
    areakey      integer not null,
    equipmentkey integer not null,
    componentkey integer not null,
    mptkey       integer not null,
    gear_mesh    real,
    fs           real,
    of           real,
    bpfi         real,
    vane         real,
    rbpf         real,
    pole         real,
    lf           real,
    bpfo         real,
    bsf          real,
    blade        real,
    belt         real,
    ftf          real
);

alter table x_a_vib_parameters
    owner to k1pledev;

create table a_vib_alarm_history
(
    alarm_id          bigint not null,
    rawdata_id        bigint not null,
    areakey           integer,
    equipmentkey      integer,
    componentkey      integer,
    mptkey            integer,
    alarm_base        integer,
    alarm_result_code integer,
    alarm_result      varchar(255),
    alarm_date        timestamp,
    treate_id         varchar(20),
    treate_datatime   timestamp,
    treate_contents   varchar(1000),
    line_category     varchar(10)
);

alter table a_vib_alarm_history
    owner to k1pledev;

create table r_vib_iso_alarm_standard
(
    areakey       integer not null,
    equipmentkey  integer not null,
    componentkey  integer not null,
    mptkey        integer not null,
    ref_ab        real,
    ref_bc        real,
    ref_cd        real,
    unit_type     integer,
    unit          varchar(10),
    line_category integer,
    disp_index    integer,
    alarm_base    integer
);

alter table r_vib_iso_alarm_standard
    owner to k1pledev;

create table r_vib_faultclass
(
    fault_order      integer not null,
    fault_class_code integer not null,
    mod_type_code    integer,
    fault_class_name varchar(256),
    mod_type_name    varchar(256)
);

alter table r_vib_faultclass
    owner to k1pledev;

create table r_vib_fault_help_advice
(
    fault_order      integer not null,
    fault_class_code integer not null,
    help_uid         integer,
    help_order       integer,
    sub_order        varchar(3),
    problem_src      varchar(255),
    help_desc        text,
    help_image       varchar(255),
    advice_desc      text,
    advice_image     varchar(255)
);

alter table r_vib_fault_help_advice
    owner to k1pledev;



create sequence waveform;

alter sequence waveform owner to k1pledev;


create table d_waveform
(
    waveformkey     bigint  default nextval('waveform'::regclass) not null,
    mptkey          integer                                       not null,
    measdt          timestamp,
    twftype         integer,
    storedt         timestamp,
    lineresolution  integer,
    deltatime       double precision,
    deltaorder      double precision,
    rpm             double precision,
    units           integer,
    unitstr         varchar(10),
    rawdata         bytea,
    overall         double precision,
    alarm_status    integer,
    onexamp         double precision,
    onexphase       double precision,
    syncrev         integer,
    syncspr         integer,
    analysisdone    boolean default false,
    recognition_yn  boolean default false,
    ml_analysisdone boolean default false
);

alter table d_waveform
    owner to k1pledev;

create table a_vib_parameters
(
    areakey         integer     not null,
    equipmentkey    integer     not null,
    componentkey    integer     not null,
    mptkey          integer     not null,
    component_type  integer     not null,
    parameter_name  varchar(30) not null,
    parameter_value varchar(255),
    constraint a_vib_paramters_new_pkey
        primary key (areakey, equipmentkey, componentkey, mptkey, component_type, parameter_name)
);

comment on table a_vib_parameters is 'parameters for analysis';

alter table a_vib_parameters
    owner to k1pledev;

create table a_vib_fault_result
(
    areakey        integer not null,
    equipmentkey   integer not null,
    componentkey   integer not null,
    mptkey         integer not null,
    waveformkey    bigint  not null,
    component_type integer not null,
    result         integer not null,
    primary key (areakey, equipmentkey, componentkey, mptkey, waveformkey, component_type)
);

alter table a_vib_fault_result
    owner to k1pledev;

create table a_vib_modules_map
(
    areakey        integer not null,
    equipmentkey   integer not null,
    componentkey   integer not null,
    mptkey         integer not null,
    component_type integer[],
    primary key (areakey, equipmentkey, componentkey, mptkey)
);

alter table a_vib_modules_map
    owner to k1pledev;

create table r_vib_analysis_module
(
    module_code integer     not null
        primary key,
    module_name varchar(30) not null
);

alter table r_vib_analysis_module
    owner to k1pledev;


