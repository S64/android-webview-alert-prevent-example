package jp.s64.android.exmaple.webviewalert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {

    private val webview by lazy { findViewById<WebView>(R.id.webview) }
    private val reload by lazy { findViewById<Button>(R.id.reload) }
    private val returnTrue by lazy { findViewById<CheckBox>(R.id.returnTrue) }
    private val forceConfirm by lazy { findViewById<CheckBox>(R.id.forceConfirm) }

    private val html = """
        <!DOCTYPE html>
        <html>
            <head>
                <meta charset="utf-8"/>
                <script type="text/javascript">
                    window.confirm('boo!');
                </script>
            </head>
            <body>
                Body
            </body>
        </html>
    """.trimIndent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webview.apply {
            settings.javaScriptEnabled = true
            webChromeClient = object : WebChromeClient() {

                override fun onJsConfirm(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    result: JsResult?
                ): Boolean {
                    if (forceConfirm.isChecked) {
                        result?.confirm()
                    }
                    return returnTrue.isChecked
                }

            }
        }

        reload.setOnClickListener {
            webview.loadData(html, "text/html", "utf-8")
        }
    }

}
