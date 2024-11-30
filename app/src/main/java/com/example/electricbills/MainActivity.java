package com.example.electricbills;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText etNumber1, etNumber2;
    Button btnCalc, btnClear;
    TextView tvOutput, labelTotalCharges, labelRebate;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();

        if (selected == R.id.menuAbout) {
            Intent aboutIntent = new Intent(MainActivity.this, About.class);
            startActivity(aboutIntent);
            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (selected == R.id.menuInstruction) {
            Intent instructionsIntent = new Intent(this, Instructions.class);
            startActivity(instructionsIntent);
            Toast.makeText(this, "Instructions clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        btnCalc = findViewById(R.id.btnCalc);
        btnClear = findViewById(R.id.btnClear);
        tvOutput = findViewById(R.id.tvOutput);
        labelTotalCharges = findViewById(R.id.labelTotalCharges);
        labelRebate = findViewById(R.id.labelRebate);

        btnCalc.setOnClickListener(v -> {
            try {
                int units = Integer.parseInt(etNumber1.getText().toString());
                double rebate = Double.parseDouble(etNumber2.getText().toString());

                if (rebate < 0 || rebate > 5) {
                    Toast.makeText(this, "Rebate percentage must be between 0 and 5.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double totalCharges = calculateCharges(units);
                double totalRebate = totalCharges * (rebate / 100.0);
                double finalCost = totalCharges - totalRebate;

                labelTotalCharges.setText(String.format("Total Charges: RM %.2f", totalCharges));
                labelRebate.setText(String.format("Total Rebate: RM %.2f", totalRebate));
                tvOutput.setText(String.format("Final Cost: RM %.2f", finalCost));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers.", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(v -> {

            etNumber1.setText("");
            etNumber2.setText("");
            labelTotalCharges.setText("Total Charges:");
            labelRebate.setText("Total Rebate:");
            tvOutput.setText("Final Cost: RM 0.00");
        });
    }

    /**
     * Method to calculate total charges based on electricity units.
     * @param units Number of units (kWh).
     * @return Total charges in RM.
     */
    private double calculateCharges(int units) {
        double total = 0.0;

        if (units > 600) {
            total += (units - 600) * 0.546;
            units = 600;
        }
        if (units > 300) {
            total += (units - 300) * 0.516;
            units = 300;
        }
        if (units > 200) {
            total += (units - 200) * 0.334;
            units = 200;
        }
        if (units > 0) {
            total += units * 0.218;
        }

        return total;
    }
}
