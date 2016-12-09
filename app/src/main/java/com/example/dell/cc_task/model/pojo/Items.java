package com.example.dell.cc_task.model.pojo;

/**
 * Created by DELL on 12/7/2016.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class Items
{


    //Tag API data

    private String has_synonyms;

    private String is_required;

    private String count;

    private String name;

    private String is_moderator_only;

    //FAV Qoues API

    private String creation_date;

    private String[] tags;

    private String title;

    private String link;

    private String last_edit_date;

    private String score;

    private String answer_count;

    private Owner owner;

    private String last_activity_date;

    private String question_id;

    private String view_count;

    private String is_answered;



    public String getHas_synonyms ()
    {
        return has_synonyms;
    }

    public void setHas_synonyms (String has_synonyms)
    {
        this.has_synonyms = has_synonyms;
    }

    public String getIs_required ()
    {
        return is_required;
    }

    public void setIs_required (String is_required)
    {
        this.is_required = is_required;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getIs_moderator_only ()
    {
        return is_moderator_only;
    }

    public void setIs_moderator_only (String is_moderator_only)
    {
        this.is_moderator_only = is_moderator_only;
    }




    public String getCreation_date ()
    {
        return creation_date;
    }

    public void setCreation_date (String creation_date)
    {
        this.creation_date = creation_date;
    }

    public String[] getTags ()
    {
        return tags;
    }

    public void setTags (String[] tags)
    {
        this.tags = tags;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getLast_edit_date ()
    {
        return last_edit_date;
    }

    public void setLast_edit_date (String last_edit_date)
    {
        this.last_edit_date = last_edit_date;
    }

    public String getScore ()
    {
        return score;
    }

    public void setScore (String score)
    {
        this.score = score;
    }

    public String getAnswer_count ()
    {
        return answer_count;
    }

    public void setAnswer_count (String answer_count)
    {
        this.answer_count = answer_count;
    }

    public Owner getOwner ()
    {
        return owner;
    }

    public void setOwner (Owner owner)
    {
        this.owner = owner;
    }

    public String getLast_activity_date ()
    {
        return last_activity_date;
    }

    public void setLast_activity_date (String last_activity_date)
    {
        this.last_activity_date = last_activity_date;
    }

    public String getQuestion_id ()
    {
        return question_id;
    }

    public void setQuestion_id (String question_id)
    {
        this.question_id = question_id;
    }

    public String getView_count ()
    {
        return view_count;
    }

    public void setView_count (String view_count)
    {
        this.view_count = view_count;
    }

    public String getIs_answered ()
    {
        return is_answered;
    }

    public void setIs_answered (String is_answered)
    {
        this.is_answered = is_answered;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [creation_date = "+creation_date+", tags = "+tags+", title = "+title+", link = "+link+", last_edit_date = "+last_edit_date+", score = "+score+", answer_count = "+answer_count+", owner = "+owner+", last_activity_date = "+last_activity_date+", question_id = "+question_id+", view_count = "+view_count+", is_answered = "+is_answered+"]";
    }
}