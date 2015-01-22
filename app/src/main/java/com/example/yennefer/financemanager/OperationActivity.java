package com.example.yennefer.financemanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yennefer.financemanager.FinanceManagerClasses.Category;
import com.example.yennefer.financemanager.FinanceManagerClasses.Operation;
import com.example.yennefer.financemanager.FinanceManagerClasses.Source;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Yennefer on 06.01.2015.
 */
public class OperationActivity extends Activity implements OnClickListener {

    private Button btnSave;
    private EditText etSum;
    private RadioButton rbOutcome, rbIncome;
    private Spinner spCategory;

    private ArrayAdapter<String> adapter;

    private String[] categoryNames;
    private String[] sourceNames;

    // Updating spinner
    private void setSpinner(String[] items) {

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        spCategory.setAdapter(adapter);
        spCategory.setSelection(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_operation_fragment);

        // Finding all Views and assign them event handlers
        btnSave = (Button) findViewById(R.id.btnSave);
        etSum = (EditText) findViewById(R.id.etSum);
        rbOutcome = (RadioButton) findViewById(R.id.rbOutcome);
        rbIncome = (RadioButton) findViewById(R.id.rbIncome);
        spCategory = (Spinner) findViewById(R.id.spCategory);

        btnSave.setOnClickListener(this);
        rbOutcome.setOnClickListener(this);
        rbIncome.setOnClickListener(this);

        // Creating lists of category and source names
        List<Category> categories = Category.listAll(Category.class);
        List<Source> sources = Source.listAll(Source.class);

        categoryNames = new String[categories.size()];
        sourceNames = new String[sources.size()];

        for (int i = 0; i < categories.size(); i++) categoryNames[i] = categories.get(i).getName();
        for (int i = 0; i < sources.size(); i++) sourceNames[i] = sources.get(i).getName();

        // Putting category names in spinner
        setSpinner(categoryNames);

    }

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
                        type = MainActivity.getOutcomeType();
                        sum = "-" + sum;
                    }
                    else {
                        source = Source.find(Source.class, "name = ?", spCategory.getSelectedItem().toString()).get(0);
                        type = MainActivity.getIncomeType();
                        sum = "+" + sum;
                    }
                    Operation operation = new Operation(sum, type, date, category, source);
                    operation.save();
                    this.finish();
                }
                else {
                    Toast.makeText(this, "Не введена сумма", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
}
