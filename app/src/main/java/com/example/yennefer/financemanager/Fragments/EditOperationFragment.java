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
import com.example.yennefer.financemanager.R;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yennefer on 25.01.2015.
 * Fragment for adding and editing operations
 */
public class EditOperationFragment extends Fragment {

    // Views
    Button btnSave;
    EditText etSum;
    RadioButton rbOutcome, rbIncome;
    Spinner spCategory;

    private String[] incomeCategoryNames;
    private String[] outcomeCategoryNames;

    private String INCOME_TYPE;
    private String OUTCOME_TYPE;

    // Updating spinner
    private void setSpinner(String[] items) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, items);

        spCategory.setAdapter(adapter);
        spCategory.setSelection(0);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Names of operation types
        INCOME_TYPE = getResources().getString(R.string.income_type);
        OUTCOME_TYPE = getResources().getString(R.string.outcome_type);

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

        // Create lists of income and outcome categories
        List<Category> categories = Category.listAll(Category.class);
        ArrayList<Category> incomeCategories = new ArrayList<Category>();
        ArrayList<Category> outcomeCategories = new ArrayList<Category>();

        for (int i = 0; i < categories.size(); i++) {
            Category currCategory = categories.get(i);
            if (currCategory.getType().getName() == OUTCOME_TYPE) {
                outcomeCategories.add(currCategory);
            } else if (currCategory.getType().getName() == INCOME_TYPE) {
                incomeCategories.add(currCategory);
            }
        }

        outcomeCategoryNames = new String[outcomeCategories.size()];
        incomeCategoryNames = new String[incomeCategories.size()];

        for (int i = 0; i < outcomeCategories.size(); i++) {
            outcomeCategoryNames[i] = outcomeCategories.get(i).getName();
        }

        for (int i = 0; i < incomeCategories.size(); i++) {
            incomeCategoryNames[i] = incomeCategories.get(i).getName();
        }

        // Putting category names in spinner
        setSpinner(outcomeCategoryNames);

        return v;
    }

    OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rbOutcome:
                    // Putting outcome category names in spinner
                    setSpinner(outcomeCategoryNames);
                    break;
                case R.id.rbIncome:
                    // Putting income category names in spinner
                    setSpinner(incomeCategoryNames);
                    break;
                case R.id.btnSave:
                    if (etSum.getText().length() != 0) {

                        // Get sum
                        BigDecimal money = new BigDecimal(etSum.getText().toString()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        String sum = money.toString();
                        if (rbOutcome.isChecked()) { sum = "-" + sum; }
                        else if (rbIncome.isChecked()) { sum = "+" + sum; }

                        // Get date
                        int date = (int) (System.currentTimeMillis() / 1000);

                        // Get category
                        Category category = Category.find(Category.class, "name = ?",
                                spCategory.getSelectedItem().toString()).get(0);

                        // Save operation
                        Operation operation = new Operation(sum, category, date);
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
