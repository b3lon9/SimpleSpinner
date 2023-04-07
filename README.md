#### SimpleSpinner

[![](https://jitpack.io/v/b3lon9/SimpleSpinner.svg)](https://jitpack.io/#b3lon9/SimpleSpinner)
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fb3lon9%2FSimpleSpinner&count_bg=%23C23B3B&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

```gradle
dependencies {
        implementation 'com.github.b3lon9:SimpleSpinner:1.0.7'
}
```
<br />

#### Use

```gradle
<com.b3lon9.app.simplespinner.SimpleSpinner
    android:id="@+id/simplespinner"
    android:layout_width="240dp"
    android:layout_height="wrap_content"/>
```

<div style="align:center">
<img src="https://user-images.githubusercontent.com/119420119/229076543-b478f73a-6606-4e2f-9feb-b5be54e290af.png" align="center" height="600">
<img src="https://user-images.githubusercontent.com/119420119/229076575-e538a0fb-c7e1-4295-a007-2d8526e44744.png" align="center" height="600">
<img src="https://user-images.githubusercontent.com/119420119/229088165-f9bcc9fd-8648-418f-b814-8dbcb20254a8.png" align="center" height="600">
</div>

<br />

#### Sample Code

```gradle
<com.b3lon9.app.simplespinner.SimpleSpinner
    android:id="@+id/simplespinner"
    android:layout_width="240dp"
    android:layout_height="wrap_content"
    android:textColor="@color/black"
    app:spinner_entries="@array/color_array"
    app:spinner_items_text_color="@color/black"
    app:spinner_items_text_size="21sp"
    app:spinner_items_gravity="center"
    app:spinner_divider_color="@color/light_gray"
    app:spinner_divider_height="1dp"/>
    
    <!--app:spinner_divider_visible="false"-->
```

<br />

#### LICENCE

```agsl
Copyright 2023 Neander

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
