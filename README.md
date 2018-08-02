# TextMarkerView

Menu? NavigationView? CheckBox? RadioButton? Just Button or TextView? No! It's TextMarkerView.

## Examples

By XML:
```
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
      <br.vince.textmarker.TextMarkerView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:setAnimDuration="250"
            app:setBackground="?attr/selectableItemBackgroundBorderless"
            app:setChecked="true"
            app:setEnableAnimation="true"
            app:setMarkerColor="@color/colorAccent"
            app:setTag="Home"
            app:setTextAppearence="@style/TextAppearance.MaterialComponents.Headline5"
            app:setTextColor="#FFFFFF"
            app:setTitle="Home"/>
...

```

By JAVA:

```
public class MainActivity extends AppCompatActivity {

  private TextMarkerView mWelcomeMenuButton;
  ...

    
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

       mWelcomeMenuButton = findViewById(R.id.welcome_home_button);
       ...
       
       setupTextMarker();
    }
    
  private void setupTextMarker() {
  
      mWelcomeMenuButton.setAnimDuration(200);
      mWelcomeMenuButton.setChecked(true);
      mWelcomeMenuButton.setInterpolator(new OvershootInterpolator(4));
      mWelcomeMenuButton.setOnAnimationListener(animationListener);
      mWelcomeMenuButton.setOnClickListenerInterception(clickListener);
      mWelcomeMenuButton.setTag("something like id");
      mWelcomeMenuButton.setText("Home");
      String text = mWelcomeMenuButton.getText();
      AppCompatTextView textView = mWelcomeMenuButton.getTextView();
      mWelcomeMenuButton.enableAnimation(true);
      mWelcomeMenuButton.removeAnimationListener();
      mWelcomeMenuButton.removeClickListenerInterception();
      
      //bonus
      //react in group
      TextMarkerGroup groupOne = new TextMarkerGroup(TextMarkerGroup.AnimateType.CHECKBOX,
           mWelcomeMenuButton, mWelcomeMenuButton2,
           mWelcomeMenuButton3, mWelcomeMenuButton4);
                
       new TextMarkerGroup(TextMarkerGroup.AnimateType.RADIOBUTTON,
           mSetupLikeCheckbox, mSetupLikeRadio);
           
       groupOne.setType(TextMarkerGroup.AnimateType.RADIOBUTTON);
       groupOne.addItem(mWelcomeMenuButton5);
       groupOne.removeItem(mWelcomeMenuButton);
  
  }

```

Well... Now, I'll explain piece by piece...

XML:

| Attr | Type | Description | Example
| ------ | ------ | ----- | ----- |
| setTextColor | color | Color text  | #FFFFFF
| setTextSize | dimension | Text size | 14sp
| setTextAppearence | reference | Text appearence  | @style/TextAppearance.MaterialComponents.Headline5
| setMarkerColor | color | Marker color | #FFFFFF
| setAnimDuration | integer | overshoot duration | 250
| setEnableAnimation | boolean | Enable animation or not | true|false
| setChecked | boolean | Set checked by default | true|false
| setBackground | reference | Set background | @drawable/my_drawable
| setTag | string | Something like an id. If you want to get from AnimationListener, this will be useful | "my tag"
| setMarkerElevation | dimension | The marker is a MaterialCard. So, you can set elevation... | 14dp

JAVA:
```

//animation duration
mMarkerText.setAnimDuration(250);

//animation interpolator
mMarkerText.setInterpolator(new OvershootInterpolator(4));

//animation status info
mMarkerText.setOnAnimationListener(new TextMarkerView.OnAnimationViewListener() {
            @Override
            public void beforeShow(@Nullable final String tag) {
                Log.d("MARKER TEXT VIEW","-> before show marker was called. MarkerView: " + tag);
            }

            @Override
            public void whenShow(@Nullable final String tag) {
                Log.d("MARKER TEXT VIEW","-> after show marker was called. MarkerView: " + tag);
            }

            @Override
            public void beforeHide(@Nullable final String tag) {
                Log.d("MARKER TEXT VIEW","-> before hide marker was called. MarkerView: " + tag);
            }

            @Override
            public void whenHide(@Nullable final String tag) {
                Log.d("MARKER TEXT VIEW","-> after hide marker was called. MarkerView: " + tag);
            }
        });
        
//remove animation controller listener        
mMarkerText.removeAnimationListener();

//set click intercept
mMarkerText.setOnClickListenerInterception(new TextMarkerView.OnClickListenerInterception() {
            @Override
            public void onClick(final TextMarker view) {
               Log.d("MARKER TEXT VIEW","-> MarkerView clicked: " + view.getText());
            }
        });
        
//remove click listener interception
mMarkerText.removeClickListenerInterception();

//set tag
mMarkerText.setTag("mMarker my text");

//set marker text
mMarkerText.setText("I can change!");

//get marker text
String markerText = mMarkerText.getText();

//get marker TextView, if you want to custom by yourself
AppCompatTextView markerTextView = mMarkerText.getTextView();

//get marker Card, if you want to custom by yourself
MaterialCardView markerCard = mMarkerText.getMarker();

//get check state
boolean isMarkerChecked = mMarkerText.isChecked();

//set marker check
mMarkerText.setChecked(true /*or false*/);

//enable or disable marker animation
mMarkerText.enableAnimation(true /*or false*/);

//now, about groups...
//initialize with components you like. First attr is the group behavior
//The group behavior can be TextMarkerGroup.AnimateType.RADIOBUTTON or
//TextMarkerGroup.AnimateType.CHECKBOX
TextMarkerGroup group = new TextMarkerGroup(TextMarkerGroup.AnimateType.RADIOBUTTON,
                mMarker1, mMarker2,
                mMarker3, mMarker4);
                
//you can send a List<TextMarkerView> to constructor too
List<TextMarkerView> list = new ArrayList<>();
list.add(mMarker1);
list.add(mMarker2);
list.add(mMarker3);

TextMarkerGroup group = new TextMarkerGroup(TextMarkerGroup.AnimateType.RADIOBUTTON, list);
                 
//you can add or remove Markers at runtime
group.addItem(mMarker5);
group.removeItem(mMarker5);

//you can change type at runtime
group.setType(TextMarkerGroup.AnimateType.CHECKBOX);
```
  
## Contribution

Pull requests are welcome!
Feel free to contribute to TextMarkerView.

If you've fixed a bug or have a feature you've added, just create a pull request.

If you've found a bug, want a new feature, or have other questions, file an issue. I will try to answer as soon as possible.

##### If you will use this library, tell me! I'll be very happy to know and put the app name here.

## License

```
MIT License

Copyright (c) 2018 Guilherme Ramos

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
