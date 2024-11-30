package com.example.electricbills;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class About extends AppCompatActivity {

    Button btnGitHubLink;

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
            // Navigate to About activity
            Intent aboutIntent = new Intent(About.this, About.class);
            startActivity(aboutIntent);
            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (selected == R.id.menuInstruction) {
            // Navigate to Instructions activity
            Intent instructionsIntent = new Intent(About.this, Instructions.class);
            startActivity(instructionsIntent);
            Toast.makeText(this, "Instructions clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Back to previous page", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);

        btnGitHubLink = findViewById(R.id.btnGitHubLink);

        // Toolbar setup with back button
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("About");
        }

        btnGitHubLink.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher_g_foreground,0,0,0);
        btnGitHubLink.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/anisysmin"));
            startActivity(browserIntent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
