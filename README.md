# TitleBar
这是一个项目中的标题栏模板,效果图如下：

![effect icon](https://github.com/wangjinggithub/TitleBar/blob/master/effect/device-2016-09-08-150529.png)

####使用方法####
<com.trywang.titlebar.views.XTitleBar
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        custom:TextCenter="带有点击事件"
        custom:isShowRight="true"
        custom:srcRight="@mipmap/icon_share"
        custom:onClickLeft="onClickLeft"
        custom:onClickCenter="onClickCenter"
        custom:onClickRight="onClickRight"/>

