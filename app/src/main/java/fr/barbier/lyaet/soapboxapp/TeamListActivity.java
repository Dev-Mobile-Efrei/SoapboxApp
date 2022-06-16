package fr.barbier.lyaet.soapboxapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.MemberModel;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.TeamModel;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.MemberRepository;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.TeamRepository;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Member;
import fr.barbier.lyaet.soapboxapp.core.domain.model.Team;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

public class TeamListActivity extends AppCompatActivity {

    private TableLayout tableLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_team_list);

        this.tableLayout = this.findViewById(R.id.teamTableLayout);

        new Thread(this::loadData).start();
    }

    private void createColumns() {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tableRow.setBackground(this.getResources().getDrawable(R.drawable.datatable_row_shape, null));

        tableRow.addView(this.createHeaderTextView(this.getResources().getString(R.string.teamList_columnName_id)));
        tableRow.addView(this.createHeaderTextView(this.getResources().getString(R.string.teamList_columnName_name)));
        tableRow.addView(this.createHeaderTextView(this.getResources()
                                                       .getString(R.string.teamList_columnName_members)));
        tableRow.addView(this.createHeaderTextView(this.getResources()
                                                       .getString(R.string.teamList_columnName_nationality)));

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

    private void fillData(@NotNull Iterable<? extends Team> teams, Collection<? extends Member> members) {
        for (Team team : teams) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tableRow.setBackground(this.getResources().getDrawable(R.drawable.datatable_row_shape, null));

            String membersString = members.stream()
                                          .filter(member -> member.getTeam().getId() == team.getId())
                                          .map(member -> member.getName() + " " +
                                                         member.getLastName().toUpperCase(Locale.ROOT) + "\n")
                                          .collect(Collectors.joining());

            tableRow.addView(this.createTextView(String.format(Locale.FRENCH, "%d", team.getId())));
            tableRow.addView(this.createTextView(team.getName()));
            tableRow.addView(this.createTextView(membersString));
            tableRow.addView(this.createTextView(team.getNationality()));

            this.tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void loadData() {
        Collection<TeamModel> teams = TeamRepository.get().getAll();
        Collection<MemberModel> members = MemberRepository.get().getAll();

        this.runOnUiThread(this::createColumns);
        this.runOnUiThread(() -> this.fillData(teams, members));
    }
}