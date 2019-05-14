#重定向跳转
URL重定向到不安全的站点,详情[参见](http://cwe.mitre.org/data/definitions/601.html)。  
例如:  

```
<?php
$redirect_url = $_GET['url'];
header("Location: " . $redirect_url);
```

然后执行:  

<pre>
http://example.com/example.php?url=http://malicious.example.com
</pre>
