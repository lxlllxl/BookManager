# BookManager
这是一个android 图书管理系统
拂晓图书说明文档


目录结构

一、登录部分
登陆界面、注册界面：
Activity_main.xml----MainActivity.java
Activity_register.xml----RegisterActivity.java




管理员登录：用户名：admin，密码：123456
Activity-admin.xml-----AdminActivity.java
点击右键头绿色的

二、前台部分
点击登录按钮，进入前台
Activity-content.xml------ContentActivity.java
                                         
白色菜单按钮显示侧滑栏
x显示书籍的用到cardView卡片式布局book.java,bookAdapter.java

里面用到circleImageview，即加载了nav_heda.Xml,nav_menu.meun
三、后台部分
Admin_content.java---Activity-content.xml

后台管理员管理三大板块，
Activity_admin_select_mesage.xml
Admin-select.-message.java

查询信息：书籍信息，借书信息，还书信息；

图书信息：admin-select-bookinfo.kjava,Activity_admin_select_bookinfo.xml


管理图书：添加图书、删除图书、修改图书
Admin_manager_book.java,Activity_admin_manager_book.xml

添加图书：admin_add_book.java----Activity_add_book.xml

删除图书：admin_delete_book.java----Activity_delete_book.xml

修改图书：admin_edit_book.java----Activity_edit_book.xml

点击item项，进入admin_updaate_book.java---Activity_update_book.xml

管理读者：增删查改读者
Admin_manager_reader.java---Activity_admin_manager_reader.xml

增加读者：Admin_add_reader.java----Activity_admin_add_reader.xml

删除读者：Admin_delete_reader.java----Activity_admin_delete_reader.xml


查找读者：Admin_select_reader.java----Activity_admin_select_reader.xml

修改读者：Admin_edit_reader.java----Activity_admin_edit_reader.xml


点击itme项进入修改界面
Admin_update_reader.java---Activity_admin_update_reader.xml


借书功能：
前台用户根据查询到的记录信息，点击listview查看详细信息
Borrow_Activity.class

点击借阅进行借书，有消息提示借书成功，并把借书记录添加到借书表borrow中，其中用shareperfence获取当前用户和系统时间，进入我的借阅里借阅信息查看借阅记录
SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日    HH:mm:ss     ");
Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
String    str    =    formatter.format(curDate);
SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
editor.putString("time",str);
editor.apply();
//借书日期
SharedPreferences perf=getSharedPreferences("data",MODE_PRIVATE);
String datetime=perf.getString("time","");//获得当前系统时间
String username=perf.getString("users","");//获得当前用户名称

还书功能
点击还书，将借阅表中的数据删除，往还书表中添加记录pay
payActivity.class

在后台管理员中查询数据，
