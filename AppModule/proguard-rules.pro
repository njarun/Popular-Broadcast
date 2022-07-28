# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

-keep class com.popular.broadcast.domain.dto.model.News { *; }
-keep class com.popular.broadcast.presentation.base.BindingAdaptersKt { *; }
-keepclassmembers class com.popular.broadcast.presentation.base.BindingAdaptersKt  { *;}
-keep class * extends com.popular.broadcast.presentation.base.BindingAdaptersKt { *; }
-keepclassmembers class * extends com.popular.broadcast.presentation.base.BindingAdaptersKt  { *;}

-keep class * extends com.popular.broadcast.ViewBinding  { *;}
-keepclassmembers class * extends com.popular.broadcast.ViewBinding  { *;}

-keep class androidx.databinding.** { *; }
-keep class * extends androidx.databinding.DataBinderMapper { *; }

-keep class * extends **.ViewBinding  { *;}
-keepclassmembers class * extends **.ViewBinding  { *;}

-keep class * extends **.ViewBindingEpoxyModelWithHolder  { *;}
-keepclassmembers class * extends **.ViewBindingEpoxyModelWithHolder { *;}

-ignorewarnings

-keepattributes Signature

-keepattributes *Annotation*

-dontwarn sun.misc.**

-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keep class com.google.gson.** { *; }
-keep class com.loopj.android.** { *; }
-keep interface com.loopj.android.** { *; }