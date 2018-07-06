# PacmanLoadingView
A pacman loading view(一个吃豆豆的loading view)。

[![Api reqeust](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/samlss/PacmanLoadingView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/PacmanLoadingView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)

<br>

  * [中文](#%E4%B8%AD%E6%96%87)
  * [English](#english)
  * [License](#license)

<br>

![gif1](https://github.com/samlss/PacmanLoadingView/blob/master/screenshots/screenshot1.gif)

![gif2](https://github.com/samlss/PacmanLoadingView/blob/master/screenshots/screenshot2.gif)

![gif3](https://github.com/samlss/PacmanLoadingView/blob/master/screenshots/screenshot3.gif)


## 中文

### 使用<br>
在根目录的build.gradle添加这一句代码：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

在app目录下的build.gradle添加依赖使用：
```
dependencies {
    implementation 'com.github.samlss:PacmanLoadingView:1.0'
}
```

布局中使用：
```
<com.iigo.library.PacmanLoadingView
                    android:id="@+id/plv_loading_1"
                    android:layout_width="80dp"
                    android:layout_height="80dp"
                    app:eater_color="@android:color/holo_red_light"
                    app:peas_color="@android:color/holo_red_light"
                    app:speed="normal" />
```

<br>

代码中使用：
```
  pacmanLoadingView.start(); //开始动画
  pacmanLoadingView.stop(); //结束动画
  pacmanLoadingView.setEaterColor(Color.BLACK); //设置吃东西的圆的颜色
  pacmanLoadingView.setPeasColor(Color.BLUE); //设置豆的颜色
```

<br>

属性说明：

| 属性        | 说明           |
| ------------- |:-------------:|
| eater_color      | 吃东西的圆的颜色|
| peas_color | 豆豆的颜色 |
| speed      | 吃的速度(normal正常，fast快，slow慢)|

<br>

如果不能满足你的需要，你可以下载源码自行修改。

## English

### Use<br>
Add it in your root build.gradle at the end of repositories：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

Add it in your app build.gradle at the end of repositories:
```
dependencies {
    implementation 'com.github.samlss:PacmanLoadingView:1.0'
}
```


in layout.xml：
```
<com.iigo.library.PacmanLoadingView
                    android:id="@+id/plv_loading_1"
                    android:layout_width="80dp"
                    android:layout_height="80dp"
                    app:eater_color="@android:color/holo_red_light"
                    app:peas_color="@android:color/holo_red_light"
                    app:speed="normal" />
```

<br>

in java code：
```
  pacmanLoadingView.start(); //start loading animation
  pacmanLoadingView.stop(); //stop loading animation
  pacmanLoadingView.setEaterColor(Color.BLACK); //set the eater's color
  pacmanLoadingView.setPeasColor(Color.BLUE); //set the pea's color
```

<br>

Attributes description：

| attr        | description  |
| ------------- |:-------------:|
| eater_color      | the eater's color|
| peas_color | the pea's color |
| speed      | the eating speed(normal,fast,slow)|

If you can not meet your needs, you can download the source code to modify it.

[id]: http://example.com/ "Optional Title Here"

## [LICENSE](https://github.com/samlss/PacmanLoadingView/blob/master/LICENSE)
