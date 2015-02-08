package com.example.yennefer.financemanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yennefer.financemanager.model.CategoryType;
import com.example.yennefer.financemanager.R;
import com.example.yennefer.financemanager.db.DatabaseManager;
import com.example.yennefer.financemanager.model.Category;
import com.example.yennefer.financemanager.model.Operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yennefer on 25.01.2015.
 * Fragment for adding and editing operations
 */

public class OperationFragment extends Fragment {

    // Views
    Button btnSave;
    EditText etSum;
    RadioButton rbOutcome, rbIncome;
    Spinner spCategory;

    List<String> incomeCategoriesNames = new ArrayList<String>();
    List<String> outcomeCategoriesNames = new ArrayList<String>();

    // Update spinner
    private void setSpinner(List<String> items) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, items);

        spCategory.setAdapter(adapter);
        spCategory.setSelection(0);

    }

    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_operation, null);

        // Find all views
        btnSave = (Button) v.findViewById(R.id.btnSave);
        etSum = (EditText) v.findViewById(R.id.etSum);
        rbOutcome = (RadioButton) v.findViewById(R.id.rbOutcome);
        rbIncome = (RadioButton) v.findViewById(R.id.rbIncome);
        spCategory = (Spinner) v.findViewById(R.id.spCategory);

        // Set up listeners to views
        btnSave.setOnClickListener(clickListener);
        rbOutcome.setOnClickListener(clickListener);
        rbIncome.setOnClickListener(clickListener);

        // Create lists of income and outcome category names
        for (Category category : DatabaseManager.getInstance().getCategoriesWithType(CategoryType.INCOME)) {
            incomeCategoriesNames.add(category.getName());
        }
        for (Category category : DatabaseManager.getInstance().getCategoriesWithType(CategoryType.OUTCOME)) {
            outcomeCategoriesNames.add(category.getName());
        }

        // Putting outcome category names in spinner
        setSpinner(outcomeCategoriesNames);

        return v;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rbOutcome:
                    // Putting outcome category names in spinner
                    setSpinner(outcomeCategoriesNames);
                    break;
                case R.id.rbIncome:
                    // Putting income category names in spinner
                    setSpinner(incomeCategoriesNames);
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
                        String categoryName = spCategory.getSelectedItem().toString();
                        Category category = DatabaseManager.getInstance().getCategoryWithName(categoryName);

                        // Save operation
                        DatabaseManager.getInstance().addOperation(new Operation(sum, category, date));

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
