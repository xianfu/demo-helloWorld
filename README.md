# demo-helloWorld
> 项目结构：

>     src
>         |_main
>             |_java
>                 |_com.xianfu
>                     |_common
>                         |_enumeration   //各种枚举类型
>                         |_exception     //异常处理类
>                         |_util          //工具类
>                     |_controller
>                         |_BaseController //_controller公用的方法
>                         |_wechat         //微信相关controller
>                     |_mapper             //mybatis接口
>                     |_pojo
>                         |_PO            //对应数据库持久层对象
>                         |_VO            //对应前端视图，视图对象（view object）
>                     |_service
>                         |_impl          //服务实现
>                     |_timejob           //定时任务
>                     |_HelloWorldApplication //项目主程序入口
>             |_resources
>                 |_META-INF
>                     |_mybatis
>                         |_mapper            //sql语句
>                 |_static                    //静态资源
>                 |_templates                 //网页、模板
>         |_test                              //测试