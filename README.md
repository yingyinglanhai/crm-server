1. 要求mysql8；
2. 新建一个数据库，比如：crm；
3. 使用navicat15+ 执行当前目录下的crm.sql脚本，初始化数据库。执行完成后，会生成100张表，4个视图。
4. 如果执行后视图没有生成，则手动运行下面的SQL生成视图。

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_candidate_candidate` AS select `i`.`TASK_ID_` AS `TASK_ID_`,`i`.`USER_ID_` AS `USER_ID` from (`act_ru_identitylink` `i` join `act_ru_task` `t`) where ((`i`.`TASK_ID_` is not null) and (`i`.`USER_ID_` is not null) and (`i`.`TASK_ID_` = `t`.`ID_`) and (`t`.`ASSIGNEE_` is null) and (`i`.`TYPE_` = 'candidate'))
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_candidate_group` AS select `i`.`TASK_ID_` AS `TASK_ID_`,`m`.`USER_ID_` AS `USER_ID` from ((`act_ru_identitylink` `i` join `act_ru_task` `t`) join `act_id_membership` `m`) where ((`i`.`TASK_ID_` = `t`.`ID_`) and (`i`.`TASK_ID_` is not null) and (`i`.`USER_ID_` is null) and (`t`.`ASSIGNEE_` is null) and (`i`.`TYPE_` = 'candidate') and (`m`.`GROUP_ID_` = `i`.`GROUP_ID_`))
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_candidate_z_distinct` AS select `v_candidate_candidate`.`TASK_ID_` AS `TASK_ID_`,`v_candidate_candidate`.`USER_ID` AS `USER_ID` from `v_candidate_candidate` union select `v_candidate_group`.`TASK_ID_` AS `TASK_ID_`,`v_candidate_group`.`USER_ID` AS `USER_ID` from `v_candidate_group`
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_tasklist` AS select `a`.`ID_` AS `TASKID`,`a`.`PROC_INST_ID_` AS `INSID`,`a`.`TASK_DEF_KEY_` AS `TASKDEFKEY`,`d`.`KEY_` AS `DEFKEY`,`d`.`NAME_` AS `DEFNAME`,`a`.`NAME_` AS `TASKNAME`,`a`.`ASSIGNEE_` AS `ASSIGNEE`,`i`.`USER_ID` AS `CANDIDATE`,`a`.`PROC_DEF_ID_` AS `DEFID`,`a`.`DELEGATION_` AS `DELEGATIONID`,`a`.`DESCRIPTION_` AS `DESCRIPTION`,`v`.`TEXT_` AS `ORDERINFO`,substring_index(`p`.`BUSINESS_KEY_`,':',1) AS `BILLTYPE`,substring_index(`p`.`BUSINESS_KEY_`,':',-(1)) AS `BUSINESSID`,date_format(`a`.`CREATE_TIME_`,'%Y-%m-%d %H:%i:%s') AS `CREATETIME`,date_format(`a`.`DUE_DATE_`,'%Y-%m-%d %H:%i:%s') AS `DUEDATE` from ((((`act_ru_task` `a` left join `v_candidate_z_distinct` `i` on((`a`.`ID_` = `i`.`TASK_ID_`))) left join `act_re_procdef` `d` on((`a`.`PROC_DEF_ID_` = `d`.`ID_`))) left join `act_ru_variable` `v` on((`a`.`PROC_INST_ID_` = `v`.`PROC_INST_ID_`))) left join `act_hi_procinst` `p` on((`a`.`PROC_INST_ID_` = `p`.`ID_`)))


1. # **架构说明**

   项目是基于若依3.8.3前后端分离版本开发的，官网是[www.ruoyi.vip](http://www.ruoyi.vip)。

想深入学习代码架构，需学习一下若依框架。

# **二、代码运行说明**

1. 配置jdk8；
1. 配置maven3.8+，最好用阿里云镜像仓库；
1. 初始化数据库，具体操作见数据库文档；
1. 用idea 导入项目代码，下载依赖；直到项目不报错；
1. 项目结构见目录下<<项目代码结构图.jpg>>所示；
   ![image](https://github.com/user-attachments/assets/563b4a6c-c55e-4dba-895a-0615bca7905b)
   1. 根节点项目的pom.xml中模块配置

`    `<modules>

`           `<module>fno-admin</module>

`           `<module>fno-framework</module>

`           `<module>fno-system</module>

`           `<module>fno-quartz</module>

`           `<module>fno-generator</module>

`           `<module>fno-common</module>

`           `<module>fno-pro</module>

`           `<module>fno-workflow</module>

`           `<module>fno-crm</module>

`    `</modules>

1. 修改fno-admin下的application-dev.yml(开发环境)和application-prod.yml(生产环境)中的mysql和redis配置信息；属性profile: 表示下载存储路径，根据需要配置windows和linux下的路径；
1. 项目启动入口是fno-admin的Application,控制台打印出"启动成功，欢迎使用"表示启动成功；
1. 项目打包参考下图，执行后，生成的fno-admin.jar在fno-admin目录下的target目录下。
   ![image](https://github.com/user-attachments/assets/2eb11f70-86d4-48c3-9d92-38433146496e)
   
# **一、如何支持SaaS多租户？**

1. 系统内置一个超级管理员admin，密码是jx123456。先用admin登录。
1. 进入系统后，找到系统管理->公司管理；
![image](https://github.com/user-attachments/assets/eca860dd-27c7-4c75-ae27-6b8309024731)

1. 来一个saas用户，就新建一个公司；

![image](https://github.com/user-attachments/assets/bdc8c407-dd0c-40b7-af6e-528d7c98731d)


1. 紧接着初始化企业，并定一个SaaS客户自己企业的管理员账号（必须只能是英文字母）；这个账号是全局唯一的，有重复会提示“该账号已经存在”；初始密码为 “123456”<br>
![image](https://github.com/user-attachments/assets/1fd52391-0093-4d3c-86b8-8394ebfed17f)

1. 然后每个SaaS客户用自己企业的管理员账号重新登录，再建的角色，用户，部门等信息都是自己企业的数据。与其它企业完全隔离。

   ![image](https://github.com/user-attachments/assets/f5b23ffe-ae90-4f69-b8f7-dd1248e399f1)

