public static class AddValueEditTextPreference extends EditTextPreference {

    public SettingsFragment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);

        EditText et = getEditText();
        et.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Dialog d = getDialog();
                int num = Integer.parseInt(s.getText().toString());
                if (d instanceof AlertDialog) {
                    AlertDialog dialog = (AlertDialog) d;
                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    // Check the EditText value
                    if (num >= 300 && num <= 2000) {
                        // Enable OK button
                        positiveButton.setEnabled(true);
                    } else {
                        // Disable the button.
                        positiveButton.setEnabled(false);
                    }
                }
            }
        });
    }
}