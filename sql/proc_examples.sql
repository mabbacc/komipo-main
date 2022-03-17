-- --------------------------------
-- 201-1
-- --------------------------------
SELECT equipmenttype, equipmentid, mptid, measdt, overall from get_comparative_status('PAF')

-- --------------------------------
-- 201-2
-- --------------------------------
SELECT equipmenttype, equipmentid, mptid, measdt, overall, units, unitstr from get_comparative_trend_itv('PAF', '10 days', '2019-11-08')
SELECT equipmenttype, equipmentid, mptid, measdt, overall, units, unitstr from get_comparative_trend_dt('PAF', '2019-10-28', '2019-11-08')

-- --------------------------------
-- 300
-- -------------------------------
SELECT mptid, mptkey, measdt, overall, units, unitstr from get_measpt_overall_status(5)
SELECT mptid, mptkey, alerthigh, faulthigh, units, unitstr FROM get_measpt_alarm_limit(9)
-- --------------------------------
-- 301
-- --------------------------------
SELECT * from public.get_overall_trend_itv(9, '10 days', '2019-11-08')
SELECT * from public.get_overall_trend_itv(13, '10 days', '2019-11-08')

SELECT * from public.get_overall_trend_dt(9, '2019-10-28', '2019-11-08')

-- -------------------------------
-- 302
-- -------------------------------
SELECT * from public.get_multi_trend_itv(9, '10 days', '2019-11-08')

SELECT * from public.get_multi_trend_dt(9, '2019-10-28', '2019-11-08')

-- --------------------------------
-- 303
-- --------------------------------
SELECT * from public.get_waveform(9, '2019-10-29 08:13:39')
select * from public.get_waveform_list(5);
-- --------------------------------
-- 304
-- --------------------------------
SELECT * from public.get_spectrum(9, '2019-10-29 08:13:39')
select * from public.get_spectrum_list(9)
-- --------------------------------
-- 305
-- --------------------------------
SELECT * from public.get_waterfall(9, '10 days', '2019-11-08')
select * from public.get_waterfall_itv(9, '10 days',)

select *
from c_area a
    left join c_equipment e on a.areakey = e.areakey
    left join c_component c on a.areakey = c.areakey and e.equipmentkey = c.equipmentkey
    left join c_measpt mpt on a.areakey = mpt.areakey and e.equipmentkey = mpt.equipmentkey and c.componentkey = mpt.componentkey
order by a.areakey, e.equipmentkey, c.componentkey, mpt.mptkey asc;
;

select * from get_system_hierarchy();
