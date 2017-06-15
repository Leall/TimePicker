TimePicker
============

 
Download
--------

```gradle
   repositories {
        ...
        maven { url "https://jitpack.io" }
   }
   dependencies {
        compile 'com.github.Leall:TimePicker:1.0'
   }
```

How do use ?
------------

```java
  new TimePicker.Builder(getSupportFragmentManager())
                .setListener(listener)
                .show();
```

```java
TimePickerListener listener = new TimePickerListener() {
        @Override
        public void onGetDate(Date date) {
        }
        @Override
        public void onCancel() {
        }
    };
```

```java
//other method
//.setMinDate()
  .setMaxDate(new Date(System.currentTimeMillis()))
  .setStartTime(2000,1,1)

```
