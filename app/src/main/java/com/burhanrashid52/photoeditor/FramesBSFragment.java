package com.burhanrashid52.photoeditor;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ja.burhanrashid52.photoeditor.PhotoEditorView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FramesBSFragment extends BottomSheetDialogFragment {

    private RecyclerView recyclerView;
    private FrameAdapter adapter;
    private ArrayList<DataModelFrames> arrayList = new ArrayList<>();
    private API APIservice;


    //PhotoEditorView editorView;
    private static final String TAG = "FramesBSFragment";

    public FramesBSFragment() {
        // Required empty public constructor
    }


    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        final View contentView = View.inflate(getContext(), R.layout.fragment_bottom_frames, null);
        dialog.setContentView(contentView);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        final CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        recyclerView = contentView.findViewById(R.id.frames_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        recyclerView.setAdapter(adapter);


        adapter = new FrameAdapter(arrayList, item -> {

         PhotoEditorView photoEditorView = Objects.requireNonNull(getActivity()).findViewById(R.id.serverimage);
         Glide.with(Objects.requireNonNull(getContext())).load(item.getImageURL()).into(photoEditorView.getSource());

            });


        getFramesData();

    }

    private void getFramesData() {

        APIservice = RetrofitClientInstance.getRetrofitInstance(getContext()).create(API.class);
        Call<List<DataModelFrames>> call = APIservice.getFrames();
        arrayList.clear();

        call.enqueue(new Callback<List<DataModelFrames>>() {
            @Override
            public void onResponse(@NonNull Call<List<DataModelFrames>> call, @NonNull Response<List<DataModelFrames>> response) {

                List<DataModelFrames> list = response.body();
                if (list != null)
                    for (DataModelFrames data:list){

                        arrayList.add(data);
                        recyclerView.setAdapter(adapter);

                    }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<List<DataModelFrames>> call, @NonNull Throwable t) {

            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        }
    }

