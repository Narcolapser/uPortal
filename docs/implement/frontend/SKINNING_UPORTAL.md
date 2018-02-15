# Skinning uPortal

## Table of Contents

1.  [Creating a skin](#creating-a-skin)
2.  [Skin Configuration](#skin-configuration)
3.  [Special Notes](#special-notes)
    1.  [Dynamic Respondr Skin](#dynamic-respondr-skin)
    2.  [Page Effects](#page-effects)

## Creating a skin

1.  Start at the root folder of the uPortal-Start.
2.  Call `./gradlew generateSkin -DskinName=wolverine` Obviously you can have the skin be named what ever you like. We will use wolverine for this example.
3.  Navigate to the *data/base/portlet-definition/* folder
4.  Edit *dynamic-respondr-skin.portlet-definition.xml* and add a `<portal-preference>` with a `<name>` of `PREFdynamicSkinName` and a `<value>` with the skin name. e.g.

    ``` xml
    <portlet-preference>
        <name>PREFdynamicSkinName</name>
        <value>wolverine</value>
    </portlet-preference>
    ```

5. Navigate to the *data/base/stylesheet-descriptor* folder
6. Edit *Respondr.stylesheet-descriptor.xml* and change the `<default-value>` to the skin name. e.g.

    ``` xml
    <stylesheet-parameter>
        <name>skin</name>
        <default-value>wolverine</default-value>
        <scope>PERSISTENT</scope>
        <description>Skin name</description>
    </stylesheet-parameter>
    ```

7. Run `./gradlew portalInit` to apply the changes to the database.
8. :warning: **Don’t forget to add the new skin to Git!**

## Skin Configuration

uPortal uses [Less variables](http://lesscss.org/features/#variables-feature) to handle global skin changes.
Changes can be made to override the [uPortal variables](/uPortal-webapp/src/main/webapp/media/skins/respondr/common/less/variables.less). Do not edit said file though. All changes should be made to the skin's `variable.less` file.

## Special Notes

### Dynamic Respondr Skin

The color variables 1-6 are the values that the dynamic respondr skin portlet customizes.

``` less
@color1
@color2
@color3
@color4
@color5
@color6
```

![Dynamic Respondr Skin Portlet Page](images/dynamic-respondr-skin.png)

### Page Effects

Portal background color and image can have special effects applied.
Setting `@portal-page-body-background-image-filter` allows for any combination [css filters](https://developer.mozilla.org/en-US/docs/Web/CSS/filter) to be applied.

![No background effect](images/background-filter-none.png)

![Sepia background effect](images/background-filter-sepia.png)
