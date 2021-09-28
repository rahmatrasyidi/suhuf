Suhuf
======

Suhuf is an android library that is used to build bottom sheets in an elegant way.

## Download

Add it in your root gradle `build.gradle` at the end of repositories

```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

And add this to your application gradle `build.gradle`

```gradle
dependencies {
    implementation "com.github.rahmatrasyidi:suhuf:$routerVersion"
}
```

If you want to get Jetpack Compose support add this to your application gradle `build.gradle`

```gradle
dependencies {
    implementation "com.github.rahmatrasyidi:suhuf-compose:$routerVersion"
}
```

## Usage

For full examples, you can refer to the `samples` app.

You can override basic sheet behaviour like `identifier`, `peekHeight`, `isCancellable`, `isDraggable`.

```kotlin
class SampleSheet : Suhuf(R.layout.sheet_sample) {
    override val identifier: String get() = IDENTIFIER // set identifier to be pass to SheetResult
    override val isCancellable: Boolean get() = true 
    override val isDraggable: Boolean get() = true
    override val peekHeight: PeekHeight get() = PeekHeight.HEIGHT_FULL // set height of sheet
}
```

For the `PeekHeight` it is only allowed to set `HEIGHT_FULL`, `HEIGHT_65` & `HEIGHT_AUTO`.
You can override Suhuf theme like this:

```kotlin
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
```

You can show and put arguments like this:

```kotlin
    val bundle = Bundle().apply {
      putString(SampleSheet.KEY_MESSAGE, "Message from arguments!")
    }
    val sheet = SampleSheet().apply {
       arguments = bundle
    }
    sheet.show(this)     
```

and get Suhuf arguments like this:

```kotlin
class SampleSheet : Suhuf(R.layout.sheet_sample) {
  override fun extractArguments(bundle: Bundle?) {
    super.extractArguments(bundle)
    val message = bundle?.getString(KEY_MESSAGE).orEmpty()
    showToast(message)
  }
}
```

Suhuf only allowed to call inside `Activity` or `Fragment`.

### Passing Result

You can pass Suhuf result back to `Fragment` or `Activity` like this:

```kotlin
class SampleSheet : Suhuf(R.layout.sheet_sample) {

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val btnPositive = view.findViewById<Button>(R.id.btnPositive)
    btnPositive.setPositiveButtonClicked()

    val btnNegative = view.findViewById<Button>(R.id.btnNegative)
    val bundle = Bundle().apply {
      putString(KEY_MESSAGE, "Message from bundle!")
    }
    btnNegative.setNegativeButtonClicked(bundle)
  }
}
```

or just call `dismissAndSendResult` from your view listener.

### Receive Result

For the result you need to implement `SheetResult` like this:

```kotlin
class SampleActivity : AppCompatActivity(), SheetActions {
...

override fun onSheetResult(identifier: String, result: SheetResult) {
        if (identifier == SampleSheet.IDENTIFIER) {
            val message = when {
                result.isResultPositive() -> "Result positive from $identifier"
                result.isResultNegative() -> {
                    result.data?.getString(SampleSheet.KEY_MESSAGE).orEmpty()
                }
                else -> "Result cancel from $identifier"
            }
            showToast(message)
        }
    }
```

### Jetpack Compose

```kotlin
class SampleComposeSheet : SuhufCompose() {

    override val identifier: String get() = IDENTIFIER
    override val isCancellable: Boolean get() = true
    override val isDraggable: Boolean get() = true
    override val peekHeight: PeekHeight get() = PeekHeight.HEIGHT_FULL

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                BasicText(
                    text = "Hello world",
                    modifier = Modifier.padding(vertical = Dp(24f)),
                    style = TextStyle(color = Color.Blue, fontSize = 36.sp)
                )
            }
        }
    }
}
```

License
-------
    Copyright (c) 2021 Rahmat Rasyidi.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
