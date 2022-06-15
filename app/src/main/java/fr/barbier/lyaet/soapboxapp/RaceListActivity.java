package fr.barbier.lyaet.soapboxapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.RaceModel;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.RaceRepository;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Race;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public class RaceListActivity extends AppCompatActivity {

    private TableLayout tableLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_race_list);
        this.tableLayout = this.findViewById(R.id.raceTableLayout);

        this.loadData();
    }

    private static @NotNull String DateToString(@NotNull Date date) {
        return date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
    }

    private void createColumns() {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tableRow.addView(this.createHeaderTextView(this.getResources().getString(R.string.columnName_id)));
        tableRow.addView(this.createHeaderTextView(this.getResources().getString(R.string.columnName_name)));
        tableRow.addView(this.createHeaderTextView(this.getResources().getString(R.string.columnName_date)));

        this.tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private @NotNull TextView createHeaderTextView(@NotNull CharSequence text) {
        TextView output = this.createTextView(text);
        output.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return output;
    }

    private @NotNull TextView createTextView(@NotNull CharSequence text) {
        TextView output = new TextView(this);
        output.setText(text);
        output.setTypeface(Typeface.DEFAULT);
        output.setPadding(5, 5, 5, 0);
        output.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        return output;
    }

    private void fillData(@NotNull Iterable<? extends Race> races) {
        for (Race race : races) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            tableRow.addView(this.createTextView(String.format(Locale.FRENCH, "%d", race.getId())));
            tableRow.addView(this.createTextView(race.getName()));
            tableRow.addView(this.createTextView(DateToString(race.getDate())));

            this.tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void loadData() {
        Collection<RaceModel> races = RaceRepository.get().getAll();

        this.createColumns();
        this.fillData(races);
    }
}