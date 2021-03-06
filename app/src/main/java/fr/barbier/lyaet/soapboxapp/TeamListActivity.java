package fr.barbier.lyaet.soapboxapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

    public void onBtnAddTeam(View view) {
        Intent intent = new Intent(this, CreateTeamActivity.class);
        this.startActivity(intent);
    }

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

        tableRow.addView(this.createHeaderTextView(this.getResources().getString(R.string.teamList_columnName_name)));
        tableRow.addView(this.createHeaderTextView(this.getResources()
                                                       .getString(R.string.teamList_columnName_members)));
        tableRow.addView(this.createHeaderTextView(this.getResources()
                                                       .getString(R.string.teamList_columnName_nationality)));
        tableRow.addView(this.createHeaderTextView(this.getResources()
                                                       .getString(R.string.teamList_columnName_picture)));

        this.tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private @NotNull TextView createHeaderTextView(@NotNull CharSequence text) {
        TextView output = this.createTextView(text);
        output.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return output;
    }

    private @NotNull ImageButton createPictureButton() {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;

        ImageButton output = new ImageButton(this);
        output.setMinimumHeight(150);
        output.setMinimumWidth(200);
        output.setLayoutParams(layoutParams);
        output.setImageResource(R.drawable.camera_logo);
        output.setPadding(5, 5, 5, 0);
        output.setOnClickListener(this::onBtnPictureClick);
        return output;
    }

    private @NotNull TextView createTextView(@NotNull CharSequence text) {
        TextView output = new TextView(this);
        output.setText(text);
        output.setTypeface(Typeface.DEFAULT);
        output.setPadding(5, 5, 5, 0);

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

            tableRow.addView(this.createTextView(team.getName()));
            tableRow.addView(this.createTextView(membersString));
            tableRow.addView(this.createTextView(team.getNationality()));
            tableRow.addView(this.createPictureButton());

            this.tableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private void loadData() {
        Collection<TeamModel> teams = TeamRepository.get().getAll();
        Collection<MemberModel> members = MemberRepository.get().getAll();

        this.runOnUiThread(this::createColumns);
        this.runOnUiThread(() -> this.fillData(teams, members));
    }

    private void onBtnPictureClick(View view) {
        try {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

            this.startActivity(intent);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}