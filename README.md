## ButterknifeDemo
Reducing View, Listener, Resources Boilerplate with Butterknife

Butterknife is a popular View "injection" library for Android. This means that the library writes common boilerplate view code for you based on annotations to save you time and significantly reduce the lines of boilerplate code written. 

### Setup

Add the following to app/build.gradle file:

```java
dependencies {
  compile 'com.jakewharton:butterknife:8.4.0'
  annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0'
}
```

Make sure to upgrade to the latest Gradle version to use the annotationProcessor syntax.

### Usage

There are three major features of ButterKnife:

    1. Improved View Lookups
    2. Improved Listener Attachments
    3. Improved Resource Lookups

### 1. Improved View Lookups
#### Activity View Lookups

Eliminate findViewById calls by using @BindView on fields:
```java
class ExampleActivity extends Activity {
  // Automatically finds each field by the specified ID.
  @BindView(R.id.title) TextView title;
  @BindView(R.id.subtitle) TextView subtitle;
  @BindView(R.id.footer) TextView footer;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.simple_activity);
    ButterKnife.bind(this);
    // TODO Use fields...
  }
}
```
#### Fragment View Lookups

This can be done within Activity, Fragment, or Adapter classes. For example, fragment usage would look like:
```java
public class FancyFragment extends Fragment {
  @BindView(R.id.button1) Button button1;
  @BindView(R.id.button2) Button button2;
  private Unbinder unbinder;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fancy_fragment, container, false);
    unbinder = ButterKnife.bind(this, view);
    // TODO Use fields...
    return view;
  }

  // When binding a fragment in onCreateView, set the views to null in onDestroyView. 
  // ButterKnife returns an Unbinder on the initial binding that has an unbind method to do this automatically.
  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
```

#### Adapter View Lookups

Within a ViewHolder inside of a ListView adapter:
```java
public class MyAdapter extends BaseAdapter {
  @Override public View getView(int position, View view, ViewGroup parent) {
    ViewHolder holder;
    if (view != null) {
      holder = (ViewHolder) view.getTag();
    } else {
      view = inflater.inflate(R.layout.whatever, parent, false);
      holder = new ViewHolder(view);
      view.setTag(holder);
    }
    holder.name.setText("John Doe");
    // etc...
    return view;
  }

  static class ViewHolder {
    @BindView(R.id.title) TextView name;
    @BindView(R.id.job_title) TextView jobTitle;

    public ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
```

This will save you the need to ever write findViewById ever again!

### 2. Improved Listener Attachments

Eliminate anonymous inner-classes for listeners by annotating methods with @OnClick and others:
```java
@OnClick(R.id.submit)
public void sayHi(Button button) {
  button.setText("Hello!");
}
```

We can attach multiple views to the same listener with:
```java
@OnClick({ R.id.door1, R.id.door2, R.id.door3 })
public void pickDoor(DoorView door) {
  if (door.hasPrizeBehind()) {
    Toast.makeText(this, "You win!", LENGTH_SHORT).show();
  } else {
    Toast.makeText(this, "Try again", LENGTH_SHORT).show();
  }
}
```

The following event listeners are supported out of the box: OnClick, OnLongClick, OnEditorAction, OnFocusChange, OnItemClick, OnItemLongClick,OnItemSelected, OnPageChange, OnTextChanged, OnTouch, OnCheckedChanged.

### 3. Improved Resource Lookups

Eliminate resource lookups in your Java code by using resource annotations on fields:

```java
class ExampleActivity extends Activity {
  @BindString(R.string.title) String title;
  @BindDrawable(R.drawable.graphic) Drawable graphic;
  @BindColor(R.color.red) int red; // int or ColorStateList field
  @BindDimen(R.dimen.spacer) Float spacer; // int (for pixel size) or float (for exact value) field
  // ...
}
```

The following resource types are available: BindArray, BindBitmap, BindBool,BindColor,BindDimen,BindDrawable,BindInt,BindString.
