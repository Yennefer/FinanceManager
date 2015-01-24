package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yennefer.financemanager.FinanceManagerClasses.Category;
import com.example.yennefer.financemanager.FinanceManagerClasses.Operation;
import com.example.yennefer.financemanager.FinanceManagerClasses.Source;
import com.example.yennefer.financemanager.R;

import java.math.BigDecimal;
import java.util.List;

public class EditOperationFragment extends Fragment {

    // Views
    Button btnSave;
    EditText etSum;
    RadioButton rbOutcome, rbIncome;
    Spinner spCategory;

    private String[] categoryNames;
    private String[] sourceNames;

    // Names of operation types
    private static final String INCOME_TYPE = "income";
    private static final String OUTCOME_TYPE = "outcome";

    // Updating spinner
    private void setSpinner(String[] items) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);

        spCategory.setAdapter(adapter);
        spCategory.setSelection(0);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.edit_operation_fragment, null);

        // Finding all Views
        btnSave = (Button) v.findViewById(R.id.btnSave);
        etSum = (EditText) v.findViewById(R.id.etSum);
        rbOutcome = (RadioButton) v.findViewById(R.id.rbOutcome);
        rbIncome = (RadioButton) v.findViewById(R.id.rbIncome);
        spCategory = (Spinner) v.findViewById(R.id.spCategory);

        btnSave.setOnClickListener(clickListener);
        rbOutcome.setOnClickListener(clickListener);
        rbIncome.setOnClickListener(clickListener);

        // Creating lists of category and source names
        List<Category> categories = Category.listAll(Category.class);
        List<Source> sources = Source.listAll(Source.class);

        categoryNames = new String[categories.size()];
        sourceNames = new String[sources.size()];

        for (int i = 0; i < categories.size(); i++) categoryNames[i] = categories.get(i).getName();
        for (int i = 0; i < sources.size(); i++) sourceNames[i] = sources.get(i).getName();

        // Putting category names in spinner
        setSpinner(categoryNames);

        return v;
    }

    OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rbOutcome:
                    // Putting category names in spinner
                    setSpinner(categoryNames);
                    break;
                case R.id.rbIncome:
                    // Putting source names in spinner
                    setSpinner(sourceNames);
                    break;
                case R.id.btnSave:
                    if (etSum.getText().length() != 0) {
                        // Saving operation in database
                        String sum;
                        String type;
                        Category category = null;
                        Source source = null;

                        BigDecimal money = new BigDecimal(etSum.getText().toString()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        sum = money.toString();

                        int date = (int) (System.currentTimeMillis() / 1000);

                        if (rbOutcome.isChecked()) {
                            category = Category.find(Category.class, "name = ?", spCategory.getSelectedItem().toString()).get(0);
                            type = OUTCOME_TYPE;
                            sum = "-" + sum;
                        }
                        else {
                            source = Source.find(Source.class, "name = ?", spCategory.getSelectedItem().toString()).get(0);
                            type = INCOME_TYPE;
                            sum = "+" + sum;
                        }
                        Operation operation = new Operation(sum, type, date, category, source);
                        operation.save();

                    }
                    else {
                        Toast.makeText(getActivity(), "Не введена сумма", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }

        }
    };

}
