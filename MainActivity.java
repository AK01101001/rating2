package dev.isnow.obrazy;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import dev.isnow.obrazy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Obraz> obrazy;
    int index;
    private boolean inChanging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        obrazy = new ArrayList<>();
        obrazy.add(new Obraz(R.drawable.first,"fishes"));
        obrazy.add(new Obraz(R.drawable.second,"fabricatorium of deep"));
        obrazy.add(new Obraz(R.drawable.third,"Moon over Septimont"));
        Show();
        binding.left.setOnClickListener(v -> {
            if (index==0)
            {
                index = obrazy.size()-1;
            }
            else
            {
                index--;

            }
            Show();
        });
        binding.right.setOnClickListener(v -> {
            if (index==obrazy.size()-1)
            {
                index = 0;
            }
            else
            {
                index++;

            }
            Show();
        });
        binding.check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            getImage().isAI = isChecked;
            binding.check.setChecked(getImage().isAI());
        });
        binding.desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                getImage().setDesc(binding.desc.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        binding.bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (!inChanging)
                {
                    getImage().AddRating(rating);
                    binding.srednia.setText(getImage().getAverge());
                }

            }
        });
    }

    private void Show() {
        inChanging = true;
        binding.srednia.setText(getImage().getAverge());
        binding.desc.setText(getImage().getDesc());
        binding.image.setImageResource(getImage().getSource());
        binding.check.setChecked(getImage().isAI());
        binding.bar.setRating(0);
        inChanging = false;
    }
    Obraz getImage()
    {
        return obrazy.get(index);
    }
}