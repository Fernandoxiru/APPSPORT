package br.ulbra.appsport;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextSteps;
    RadioGroup radioGroupStepSize;
    RadioGroup radioGroupSimNao;
    TextView textViewResult;

    private void calculateDistance() {
        String stepsStr = editTextSteps.getText().toString();
        if (stepsStr.isEmpty()) {
            textViewResult.setText("Por favor, insira a quantidade de passos.");
            return;
        }

        int steps = Integer.parseInt(stepsStr);
        double stepLength = 0.0;

        if (radioGroupStepSize.getCheckedRadioButtonId() == R.id.rbcurto) {
            stepLength = 0.5;
        } else if (radioGroupStepSize.getCheckedRadioButtonId() == R.id.rbmedio) {
            stepLength = 0.7;
        } else if (radioGroupStepSize.getCheckedRadioButtonId() == R.id.rblongo) {
            stepLength = 1.0;
        } else {
            textViewResult.setText("Por favor, selecione o tamanho do passo.");
            return;
        }

        double distance = steps * stepLength;

        if (radioGroupSimNao.getCheckedRadioButtonId() == R.id.rbSim) {
            distance *= 1.10;
        }

        textViewResult.setText(String.format("Distância estimada: %.2f m", distance));
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSteps = findViewById(R.id.edquantidade);
        radioGroupStepSize = findViewById(R.id.rgOpcoes);
        radioGroupSimNao = findViewById(R.id.rgsimnao);
        textViewResult = findViewById(R.id.txtResultado);

        Button buttonCalcular = findViewById(R.id.btncalcular);
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDistance();
            }
        });
    }
}
