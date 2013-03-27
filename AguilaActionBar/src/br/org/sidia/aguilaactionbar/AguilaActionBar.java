package br.org.sidia.aguilaactionbar;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AguilaActionBar extends RelativeLayout {

    private final LayoutInflater mInflater;

    private final ImageButton mLogoView;

    private final TextView mTitleView;

    private final LinearLayout mActionIconContainer;
    public static AguilaActionBar instanceActionBar;

    //private final Typeface fontStyle;

    public AguilaActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        RelativeLayout barView = (RelativeLayout) mInflater.inflate(R.layout.aguila_actionbar, null);
        addView(barView);

        mLogoView = (ImageButton) barView.findViewById(R.id.actionbar_home_logo);
        mTitleView = (TextView) barView.findViewById(R.id.actionbar_title);
        mActionIconContainer = (LinearLayout) barView.findViewById(R.id.actionbar_actionIcons);

        //fontStyle = Typeface.createFromAsset(context.getAssets(), "fonts/robotoregular.ttf");

        instanceActionBar = this;
    }

    public void setHomeLogo(int resId, int tag) {
        setHomeLogo(resId, null, tag);
    }

    public void setHomeLogo(int resId, OnClickListener onClickListener, int tag) {
        mLogoView.setImageResource(resId);
        mLogoView.setVisibility(View.VISIBLE);
        mLogoView.setTag(tag);
        mLogoView.setOnClickListener(onClickListener);
        if (onClickListener != null) {
        }
    }

    public void setTitle(CharSequence title) {
        mTitleView.setText(title);
        //mTitleView.setTypeface(fontStyle);
        setSizeTitle(18);
    }

    public void setTitle(int resid) {
        mTitleView.setText(resid);
        //mTitleView.setTypeface(fontStyle);
        setSizeTitle(18);
    }

    public void setSizeTitle(int size) {
        mTitleView.setTextSize(size);
    }

    public View addActionIcon(int iconResourceId, boolean enabled, int visibility, OnClickListener onClickListener,
        int tag) {
        View view = mInflater.inflate(R.layout.aguila_actionbar_icon, mActionIconContainer, false);
        ImageButton imgButton = (ImageButton) view.findViewById(R.id.actionbar_item);
        imgButton.setTag(tag);
        imgButton.setEnabled(enabled);
        imgButton.setVisibility(visibility);
        imgButton.setImageResource(iconResourceId);
        imgButton.setOnClickListener(onClickListener);

        mActionIconContainer.addView(view, mActionIconContainer.getChildCount());
        return imgButton;
    }

    public void addActionEdiText(TextWatcher watcher, int width, int height) {
        View view = mInflater.inflate(R.layout.aguila_actionbar_search, mActionIconContainer, false);
        EditText searchField = (EditText) view.findViewById(R.id.actionbar_search);
        // searchField.setLayoutParams(new LayoutParams(width, height));
        searchField.addTextChangedListener(watcher);
        searchField.requestFocus();
        searchField.setHint(getContext().getString(R.string.searchFor));
        //searchField.setTypeface(fontStyle);
        mActionIconContainer.addView(view, mActionIconContainer.getChildCount());
    }

    public boolean removeAllItemsActionIcon() {
        int count = mActionIconContainer.getChildCount();
        for(int i = 0; i < count; i++) {
            mActionIconContainer.removeViewAt(0);
        }
        return false;
    }

    public int getIndexActionIconByTag(int tag) {
        int count = mActionIconContainer.getChildCount();
        int index = 0;
        for(int i = 0; i < count; i++) {
            if (tag == Integer.parseInt((String) mActionIconContainer.getChildAt(i).getTag()))
                index = i;
        }
        return index;
    }

    public ImageButton getActionIconByTag(Integer tag) {
        int count = mActionIconContainer.getChildCount();
        ImageButton retorno = null;
        ImageButton view = null;
        Integer tagx = 0;
        for(int i = 0; i < count; i++) {
            if (mActionIconContainer.getChildAt(i) instanceof ImageButton) {
                view = (ImageButton) mActionIconContainer.getChildAt(i);
                tagx = (Integer) view.getTag();
                if (tag == tagx)
                    retorno = view;
            }
        }
        return retorno;
    }

    public Boolean verifyIconByTag(Integer tag) {
        int count = mActionIconContainer.getChildCount();
        Boolean retorno = false;
        ImageButton view = null;
        Integer tagx = 0;
        for(int i = 0; i < count; i++) {
            if (mActionIconContainer.getChildAt(i) instanceof ImageButton) {
                view = (ImageButton) mActionIconContainer.getChildAt(i);
                tagx = (Integer) view.getTag();
                if (tag == tagx)
                    retorno = true;
            }
        }
        return retorno;
    }

    public EditText getEditText() {
        int count = mActionIconContainer.getChildCount();
        EditText retorno = null;
        for(int i = 0; i < count; i++) {
            if (mActionIconContainer.getChildAt(i) instanceof EditText) {
                retorno = (EditText) mActionIconContainer.getChildAt(i);
            }
        }
        return retorno;
    }

    public boolean removeActionIconAt(int index) {
        int count = mActionIconContainer.getChildCount();
        if (count > 0 && index >= 0 && index < count) {
            mActionIconContainer.removeViewAt(index);
            return true;
        }
        return false;
    }

    public void removeActionIconByTag(int tag) {
        int index = getIndexActionIconByTag(tag);
        removeActionIconAt(index);
    }

    public void setEditText(String texto) {
        EditText editText = getEditText();
        editText.setText(texto);
    }

    public void setEditTextLayout(int w, int h) {
        EditText editText = getEditText();
        // LayoutParams layout = new LayoutParams(w, h);
        // layout.setMargins(50, 10, 0, 0);
        editText.getLayoutParams().width = w;
        editText.getLayoutParams().height = h;
        // editText.setTextSize(12);

        // editText.setLayoutParams(new LayoutParams(w, h));
    }

    public String getEditTextContent() {
        EditText editText = getEditText();
        return editText.getText().toString().trim();
    }

    public void setActionIcon(int tag, int iconResourceId, int newtag, boolean enabled, int visibility) {
        try {
            ImageButton imgButton = getActionIconByTag(tag);
            imgButton.setImageResource(iconResourceId);
            imgButton.setTag(newtag);
            imgButton.setEnabled(enabled);
            imgButton.setVisibility(visibility);
        } catch(Exception e) {

        }

    }

    public String getTitle() {
        return mTitleView.getText().toString();
    }
}
