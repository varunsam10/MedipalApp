package iss.medipal.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iss.medipal.MediPalApplication;
import iss.medipal.R;
import iss.medipal.constants.Constants;
import iss.medipal.dao.MedicineDao;
import iss.medipal.dao.impl.MedicineDaoImpl;
import iss.medipal.model.Medicine;
import iss.medipal.ui.activities.MainActivity;
import iss.medipal.ui.adapters.MedicineListAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewMedicineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewMedicineFragment extends Fragment {

    private FloatingActionButton addMedicineButton;
    private ListView medicineList;
    private FrameLayout innerLayout;
    private MedicineListAdapter medicineListAdapter;
    private ArrayList<Medicine> medicines;
    private List<String> medicineNames;
    private MedicineDao medicineDao;

    public ViewMedicineFragment() {
        // Required empty public constructor
    }

    public static ViewMedicineFragment newInstance() {
        ViewMedicineFragment fragment = new ViewMedicineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_view_medicine, container, false);
        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialiseUI(view);
        setListeners();
        setList();
    }

    private void initialiseUI(View view){
        addMedicineButton=(FloatingActionButton)view.findViewById(R.id.addMedicine);
        medicineList=(ListView)view.findViewById(R.id.medicineList);
        innerLayout=(FrameLayout)view.findViewById(R.id.add_medicine_frame);
    }

    private void setList(){
        medicines = MediPalApplication.getPersonStore().getmPersonalBio().getMedicines();
        if(medicines==null)
        {
            medicines=new ArrayList<>();
        }
        medicineListAdapter = new MedicineListAdapter(getContext(), medicines);
        medicineList.setAdapter(medicineListAdapter);
    }

    public void setListeners() {
        final View.OnClickListener addMedicineEvent=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMedicineFragment addMedicineTab=AddMedicineFragment.newInstance();
                FragmentManager manager=getChildFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.add_medicine_frame,addMedicineTab,Constants.ADD_MEDICINE_PAGE).commit();
                innerLayout.setVisibility(View.VISIBLE);
                addMedicineButton.setVisibility(View.GONE);
            }
        };


        FrameLayout.OnLayoutChangeListener layoutChangeListener=new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                MainActivity activity=(MainActivity) v.getContext();
                if(activity.getmListener()==null)
                {
                    addMedicineButton.setVisibility(View.VISIBLE);
                    innerLayout.setVisibility(View.GONE);

                    Log.d("Fragment value","hello");
                }
            }
        };

        ListView.OnItemClickListener itemClickListener=new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getContext(),  "position"+position+"id"+id, Toast.LENGTH_SHORT).show();
                AddMedicineFragment addMedicineTab=AddMedicineFragment.newInstance(medicines.get(position+1));

                FragmentManager manager=getChildFragmentManager();
                FragmentTransaction transaction= manager.beginTransaction();
                transaction.replace(R.id.add_medicine_frame,addMedicineTab,Constants.ADD_MEDICINE_PAGE).commit();

                innerLayout.setVisibility(View.VISIBLE);
                addMedicineButton.setVisibility(View.INVISIBLE);
            }
        };
         medicineList.setOnItemClickListener(itemClickListener);
         innerLayout.addOnLayoutChangeListener(layoutChangeListener);
         addMedicineButton.setOnClickListener(addMedicineEvent);
    }

}