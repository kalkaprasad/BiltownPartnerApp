package com.cscodetech.partner.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.cscodetech.partner.R;
import com.cscodetech.partner.model.LoginUser;
import com.cscodetech.partner.model.User;
import com.cscodetech.partner.retrofit.APIClient;
import com.cscodetech.partner.retrofit.GetResult;
import com.cscodetech.partner.utils.CustPrograssbar;
import com.cscodetech.partner.utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;


public class ProfileFragment extends Fragment implements GetResult.MyListener {

    @BindView(R.id.ed_firstname)
    EditText edFirstname;
    @BindView(R.id.ed_email)
    EditText edEmail;
    @BindView(R.id.ed_mobile)
    EditText edMobile;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.ed_service)
    EditText edService;
    @BindView(R.id.btn_countinue)
    TextView btnCountinue;

    SessionManager sessionManager;
    CustPrograssbar custPrograssbar;
    User user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        custPrograssbar = new CustPrograssbar();
        sessionManager = new SessionManager(getActivity());
        user = sessionManager.getUserDetails("");
        edFirstname.setText("" + user.getName());
        edEmail.setText("" + user.getEmail());
        edMobile.setText("" + user.getMobile());
        edAddress.setText("" + user.getAddress());
        edPassword.setText("" + user.getPassword());
        edService.setText("" + user.getCategory());

        return view;
    }

    private void updateUser() {
        if (!validation()) {
            return;
        }
        custPrograssbar.prograssCreate(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", user.getId());
            jsonObject.put("name", edFirstname.getText().toString());
            jsonObject.put("password", edPassword.getText().toString());
            jsonObject.put("address", edAddress.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody bodyRequest = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        Call<JsonObject> call = APIClient.getInterface().updateProfile(bodyRequest);
        GetResult getResult = new GetResult();
        getResult.setMyListener(this);
        getResult.callForLogin(call, "1");

    }

    @OnClick({R.id.btn_countinue})
    public void onClick(View view) {
        if (view.getId() == R.id.btn_countinue) {

            updateUser();
        }
    }

    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            custPrograssbar.closePrograssBar();
            if (callNo.equalsIgnoreCase("1")) {
                Gson gson = new Gson();
                LoginUser loginUser = gson.fromJson(result.toString(), LoginUser.class);
                Toast.makeText(getActivity(), loginUser.getResponseMsg(), Toast.LENGTH_LONG).show();
                if (loginUser.getResult().equalsIgnoreCase("true")) {
                    User user = loginUser.getUser();
                    user.setCategory(loginUser.getCategory());
                    sessionManager.setUserDetails("", user);
                    user = sessionManager.getUserDetails("");
                    edFirstname.setText("" + user.getName());
                    edEmail.setText("" + user.getEmail());
                    edMobile.setText("" + user.getMobile());
                    edAddress.setText("" + user.getAddress());
                    edPassword.setText("" + user.getPassword());
                }
            }
        } catch (Exception e) {
            Log.e("Error", "-->" + e.toString());

        }
    }

    public boolean validation() {
        if (TextUtils.isEmpty(edFirstname.getText().toString())) {
            edFirstname.setError("Enter Name");
            return false;
        }
        if (TextUtils.isEmpty(edPassword.getText().toString())) {
            edPassword.setError("Enter Password");
            return false;
        }
        if (TextUtils.isEmpty(edAddress.getText().toString())) {
            edAddress.setError("Enter Address");
            return false;
        }
        return true;
    }
}