package fr.barbier.lyaet.soapboxapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.MemberModel;
import fr.barbier.lyaet.soapboxapp.core.Application.Business.TeamModel;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.MemberRepository;
import fr.barbier.lyaet.soapboxapp.core.Application.repository.TeamRepository;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class CreateTeamActivity extends AppCompatActivity {

    private TableLayout membersTableLayout = null;

    public void onBtnAddMemberClick(View view) {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tableRow.setBackground(this.getResources().getDrawable(R.drawable.datatable_row_shape, null));

        tableRow.addView(this.createEditTextCell());
        tableRow.addView(this.createEditTextCell());

        this.membersTableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void onBtnCreateTeamClick(View view) {
        if (!this.validateData()) {
            return;
        }

        String name = ((TextView) this.findViewById(R.id.editTeamName)).getText().toString();
        String nationality = ((TextView) this.findViewById(R.id.editTeamNationality)).getText().toString();

        Collection<MemberModel> members = new ArrayList<>();

        int nbMembers = this.membersTableLayout.getChildCount();
        for (int i = 1; i < nbMembers; i++) {
            TableRow row = (TableRow) this.membersTableLayout.getChildAt(i);

            String firstName = ((TextView) row.getChildAt(0)).getText().toString();
            String lastName = ((TextView) row.getChildAt(1)).getText().toString();

            members.add(MemberModel.create(firstName, lastName));
        }

        TeamModel team = TeamModel.create(name, nationality);

        Thread createTeamThread = new Thread(() -> {
            TeamRepository.get().insert(team);

            members.forEach(member -> {
                member.setTeam(team);
                MemberRepository.get().insert(member);
            });
        });
        createTeamThread.start();

        this.returnToList();
    }

    public void returnToList() {
        Intent intent = new Intent(this, TeamListActivity.class);
        this.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_create_team);

        this.membersTableLayout = this.findViewById(R.id.tableLayoutMembers);
        this.createColumns();
    }

    private void createColumns() {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tableRow.setBackground(this.getResources().getDrawable(R.drawable.datatable_row_shape, null));

        tableRow.addView(this.createHeaderTextView("Prénom"));
        tableRow.addView(this.createHeaderTextView("Nom"));

        this.membersTableLayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private @NotNull EditText createEditTextCell() {
        EditText output = new EditText(this);
        output.setPadding(5, 5, 5, 5);
        output.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        return output;
    }

    private @NotNull TextView createHeaderTextView(@NotNull CharSequence text) {
        TextView output = new TextView(this);
        output.setText(text);
        output.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        output.setPadding(5, 5, 5, 0);
        output.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        return output;
    }

    private boolean validateData() {
        EditText editTeamName = this.findViewById(R.id.editTeamName);
        EditText editTeamNationality = this.findViewById(R.id.editTeamNationality);

        if (editTeamName.getText().toString().isEmpty() || editTeamNationality.getText().toString().isEmpty()) {
            return false;
        }

        int nbMembers = this.membersTableLayout.getChildCount();
        for (int i = 1; i < nbMembers; i++) {
            TableRow row = (TableRow) this.membersTableLayout.getChildAt(i);

            if (((TextView) row.getChildAt(0)).getText().toString().isEmpty() ||
                ((TextView) row.getChildAt(1)).getText().toString().isEmpty()) {
                return false;
            }
        }

        return true;
    }
}