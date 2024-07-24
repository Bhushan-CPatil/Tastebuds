package com.bhushan.tastebuds.ui.customview

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

class ReadMoreTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    private var originalText: CharSequence? = null
    private var truncatedText: CharSequence? = null
    private var isTruncated = true

    var maxLinesForTruncate = 2
    var readMoreText = "... Read More"
    var readLessText = " Read Less"

    fun setReadMoreText(fullText: CharSequence) {
        originalText = fullText
        truncatedText = getTruncatedText(fullText)
        setText(truncatedText)
        movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getTruncatedText(text: CharSequence): CharSequence {
        val maxLines = maxLinesForTruncate
        val layout = layout
        if (layout == null || layout.lineCount <= maxLines) {
            return text
        }
        val endPosition = layout.getLineEnd(maxLines - 1)
        val truncatedText = text.subSequence(0, endPosition).toString()
            .substringBeforeLast(" ") + readMoreText
        val spannableString = SpannableStringBuilder(truncatedText)
        spannableString.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                setText(originalText)
                append(readLessText)
                movementMethod = LinkMovementMethod.getInstance()
                isTruncated = false
            }
        }, truncatedText.length - readMoreText.length, truncatedText.length, 0)
        return spannableString
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        if (isTruncated && !TextUtils.isEmpty(originalText)) {
            super.setText(truncatedText, type)
        } else {
            val spannableString = SpannableStringBuilder(text)
            spannableString.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    setText(truncatedText)
                    movementMethod = LinkMovementMethod.getInstance()
                    isTruncated = true
                }
            }, text!!.length - readLessText.length, text.length, 0)
            super.setText(spannableString, type)
        }
    }
}