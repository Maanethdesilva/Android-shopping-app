package com.example.javaproject;

import androidx.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyModel implements Contract.Model {
    private Contract.Presenter presenter;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public MyModel(Contract.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void loginChecker(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (task.isSuccessful()){
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("Users");
                user_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean isStoreOwner = snapshot.child(userID).child("isStoreOwner").getValue(boolean.class);
                        if (isStoreOwner){
                            presenter.navigateStore();
                        }else {
                            presenter.navigateCustomer();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }else {
                presenter.unsuccessful();
            }
        });
    }
}
