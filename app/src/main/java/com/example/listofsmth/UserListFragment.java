package com.example.listofsmth;

import android.os.Bundle;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import java.util.List;

public class UserListFragment extends Fragment {
    private RecyclerView userRecyclerView;
    private UserAdapter userAdapter;
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_user_list,viewGroup,false);
        userRecyclerView = view.findViewById(R.id.userRecyclerView);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        UserList userList = UserList.get();
        List<User> users = userList.getUsers();
        userAdapter = new UserAdapter(users);
        userRecyclerView.setAdapter(userAdapter);


        return view;

    }

    private class UserHolder extends RecyclerView.ViewHolder{
        private TextView userItem;
        public UserHolder (LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.list_item_user,viewGroup,false));
            userItem = itemView.findViewById(R.id.textView_UserItem);

        }
        public void bind(User user){
            String userName = "Name "+user.getUserName()+" LastName "+ user.getUserLastName()+"\n_____________________";
            userItem.setText(userName);

        }
    }
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        private List<User> users ;
        public UserAdapter(List <User> users){
         this.users = users;
        }

        @NonNull
        @Override
        public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position){
            User user = users.get(position);
            userHolder.bind(user);

        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}
