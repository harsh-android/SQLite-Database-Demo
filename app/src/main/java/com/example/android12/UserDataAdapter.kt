package com.example.android12

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class UserDataAdapter(list: ArrayList<UserModel>) :
    RecyclerView.Adapter<UserDataAdapter.UserDataHolder>() {
    var modellist = list
    lateinit var context: Context
    lateinit var database: Database

    class UserDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNameSurname = itemView.findViewById<TextView>(R.id.txtNameSurname)
        var txtAddress = itemView.findViewById<TextView>(R.id.txtAddress)
        var btnUpdate = itemView.findViewById<Button>(R.id.btnUpdate)
        var btnDelete = itemView.findViewById<Button>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataHolder {
        context = parent.context
        return UserDataHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: UserDataHolder, position: Int) {
        holder.txtNameSurname.text =
            modellist.get(position).name + " " + modellist.get(position).surname
        holder.txtAddress.text = modellist.get(position).address
        database = Database(context)
        holder.btnUpdate.setOnClickListener {
            var dialog = Dialog(context)
            dialog.setContentView(R.layout.update_dialog)
            dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))
            var idd = dialog.findViewById<TextView>(R.id.txtId)
            var edtName = dialog.findViewById<TextView>(R.id.name)
            var edtSurname = dialog.findViewById<TextView>(R.id.surname)
            var edtAddress = dialog.findViewById<TextView>(R.id.address)
            var btnUpdates = dialog.findViewById<Button>(R.id.btnUpdate)

            idd.text = modellist.get(position).id.toString()
            edtName.text = modellist.get(position).name.toString()
            edtSurname.text = modellist.get(position).surname.toString()
            edtAddress.text = modellist.get(position).address.toString()

            btnUpdates.setOnClickListener {

                database.updateData(
                    modellist.get(position).id,
                    edtName.text.toString(),
                    edtSurname.text.toString(),
                    edtAddress.text.toString()
                )
                MainActivity.Updated()
                dialog.dismiss()

            }

            dialog.show()

        }

        holder.btnDelete.setOnClickListener {
            database.deleteData(modellist.get(position).id)
            MainActivity.Updated()
        }

    }

    override fun getItemCount(): Int {
        return modellist.size
    }

    fun update(list: java.util.ArrayList<UserModel>) {
        modellist = list
        notifyDataSetChanged()
    }

}