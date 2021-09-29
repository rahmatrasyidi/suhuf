package com.rahmatrasyidi.suhuf

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rahmatrasyidi.suhuf.data.PeekHeight
import com.rahmatrasyidi.suhuf.data.SheetResult
import com.rahmatrasyidi.suhuf.interfaces.SheetActions
import com.rahmatrasyidi.suhuf.interfaces.SheetBehaviour
import com.rahmatrasyidi.suhuf.interfaces.SheetCallback

abstract class Suhuf(
    @LayoutRes private val contentRes: Int = -1
) : BottomSheetDialogFragment(), SheetBehaviour {

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override val peekHeight: PeekHeight get() = PeekHeight.HEIGHT_AUTO
    override val isCancellable: Boolean get() = true
    override val isDraggable: Boolean get() = true
    override val identifier: String get() = this::class.java.simpleName

    private var sheetActions: SheetActions? = null
    private var sheetCallback: SheetCallback? = null
    private var sheetResult = SheetResult()

    private val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            sheetCallback?.onStateChanged(bottomSheet, newState)
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            sheetCallback?.onSlide(bottomSheet, slideOffset)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractArguments(arguments)
    }

    open fun extractArguments(bundle: Bundle?) {
        // override
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (contentRes != -1) {
            inflater.inflate(contentRes, container, false)
        } else {
            throw IllegalStateException("Layout resource must be attached!")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SheetActions) {
            sheetActions = context
        }
    }

    override fun onDetach() {
        sheetActions = null
        (dialog as? BottomSheetDialog)?.behavior?.removeBottomSheetCallback(this.bottomSheetCallback)
        super.onDetach()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dialog = (requireDialog() as BottomSheetDialog)
        setPeekHeight(dialog)
        dialog.dismissWithAnimation = true
        dialog.behavior.isDraggable = isDraggable
        dialog.behavior.isHideable = isCancellable
        dialog.behavior.saveFlags = BottomSheetBehavior.SAVE_ALL
        dialog.behavior.addBottomSheetCallback(this.bottomSheetCallback)

        requireDialog().setCancelable(isCancellable)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme)
    }

    fun addSheetCallback(sheetCallback: SheetCallback) {
        this.sheetCallback = sheetCallback
    }

    fun show(activity: Activity) {
        if (activity is AppCompatActivity) {
            show(activity.supportFragmentManager, this.identifier)
        }
    }

    fun show(fragment: Fragment) {
        show(fragment.requireActivity().supportFragmentManager, this.identifier)
    }

    fun setOnSheetResultListener(result: (SheetResult) -> Unit) {
        result.invoke(sheetResult)
    }

    protected fun setResult(resultCode: Int, data: Bundle? = null) {
        sheetResult = sheetResult.copy(resultCode = resultCode, data = data)
    }

    protected fun dismissAndSendResult() {
        sheetActions?.onSheetResult(identifier, sheetResult)
        dismiss()
    }

    protected fun View.setPositiveButtonClicked(bundle: Bundle? = null) {
        this.setOnClickListener {
            setResult(resultCode = SheetResult.RESULT_CODE_POSITIVE, bundle)
            dismissAndSendResult()
        }
    }

    protected fun View.setNegativeButtonClicked(bundle: Bundle? = null) {
        this.setOnClickListener {
            setResult(resultCode = SheetResult.RESULT_CODE_NEGATIVE, bundle)
            dismissAndSendResult()
        }
    }

    protected fun View.setCloseButtonClicked(bundle: Bundle? = null) {
        this.setOnClickListener {
            setResult(resultCode = SheetResult.RESULT_CODE_CANCELLED, bundle)
            dismissAndSendResult()
        }
    }

    private fun setPeekHeight(dialog: BottomSheetDialog) {
        when (peekHeight) {
            PeekHeight.HEIGHT_FULL -> {
                dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
                dialog.behavior.isFitToContents = false
                val lp = (view?.parent as? ViewGroup)?.layoutParams
                lp?.height = getDeviceHeight() - 24.dp
            }

            PeekHeight.HEIGHT_65 -> {
                dialog.behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                val maxHeight = (getDeviceHeight() * 0.65).toInt()
                setPeekHeight(dialog, maxHeight)
            }

            PeekHeight.HEIGHT_AUTO -> {
                dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
                dialog.behavior.isFitToContents = true
            }
        }
    }

    private fun setPeekHeight(dialog: BottomSheetDialog, maxHeight: Int) {
        dialog.behavior.isFitToContents = false
        dialog.behavior.setPeekHeight(maxHeight, true)
        (view?.parent as? ViewGroup)?.layoutParams?.height = maxHeight
    }

    private fun getDeviceHeight(): Int {
        val displayMetrics = requireContext().resources.displayMetrics
        return displayMetrics.heightPixels
    }

    private val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

    private val Float.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
}