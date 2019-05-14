#不正确输入检验
用户可以对输入进行控制,从而可能引发XSS攻击。例如:  

```
$birthday = $_GET['birthday'];
$homepage = $_GET['homepage'];
echo "Birthday: $birthday<br>Homepage: <a href=$homepage>click here</a>"
```