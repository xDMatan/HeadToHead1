package com.example.headtohead.question;

import java.util.ArrayList;
import java.util.HashMap;

public class GameCollections {
    private ArrayList<ClassicQuestion> classisCollection;
    private ArrayList<TFquestion> tFCollection;

    public GameCollections(){
        classisCollection = new ArrayList<ClassicQuestion>();
        tFCollection = new ArrayList<TFquestion>();


        HashMap<String, Boolean> ClassQ1 = new HashMap<String, Boolean>();
        ClassQ1.put("אדום", true);
        ClassQ1.put("כתום", true);
        ClassQ1.put("צהוב", true);
        ClassQ1.put("ירוק", true);
        ClassQ1.put("כחול", true);
        ClassQ1.put("סגול", true);
        ClassQ1.put("תכלת", true);
        ClassicQuestion q1 = new ClassicQuestion("מהם צבעי הקשת?", "General", ClassQ1, false);
        classisCollection.add(q1);
        HashMap<String, Boolean> ClassQ2 = new HashMap<String, Boolean>();
        ClassQ2.put("אסיה", true);
        ClassQ2.put("אירופה", true);
        ClassQ2.put("אפריקה", true);
        ClassQ2.put("אמריקה הצפונית", true);
        ClassQ2.put("אמריקה הדרומית", true);
        ClassQ2.put("אוסטרליה", true);
        ClassQ2.put("אנטרקטיקה", true);
        ClassicQuestion q2 = new ClassicQuestion("מהם היבשות בעולם?", "Geography", ClassQ2, false);
        classisCollection.add(q2);
        HashMap<String, Boolean> ClassQ3 = new HashMap<String,Boolean>();
        ClassQ3.put("אוקטובר",true);
        ClassQ3.put("נובמבר",true);
        ClassQ3.put("מרץ",true);
        ClassQ3.put("ינואר",true);
        ClassQ3.put("פברואר",true);
        ClassQ3.put("אפריל",true);
        ClassQ3.put("מאי",true);
        ClassQ3.put("יוני",true);
        ClassQ3.put("יולי",true);
        ClassQ3.put("אוגוסט",true);
        ClassQ3.put("ספטמבר",true);
        ClassQ3.put("דצמבר",true);

        ClassicQuestion q3 = new  ClassicQuestion("מהם חודשי השנה","General",ClassQ3,false);
        classisCollection.add(q3);
        HashMap<String, Boolean> ClassQ4 = new HashMap<String, Boolean>();
        ClassQ4.put("מרקורי", true);
        ClassQ4.put("חמה", true);
        ClassQ4.put("ארץ", true);
        ClassQ4.put("מאדים", true);
        ClassQ4.put("יופיטר", true);
        ClassQ4.put("שבתאי", true);
        ClassQ4.put("אורנוס", true);
        ClassQ4.put("נפטון", true);
        ClassicQuestion q4 = new ClassicQuestion("מהם כוכבי הלכת במערכת השמש?", "Astronomy", ClassQ4, false);
        classisCollection.add(q4);
        HashMap<String, Boolean> ClassQ5 = new HashMap<String, Boolean>();
        ClassQ5.put("נצרות", true);
        ClassQ5.put("אסלאם", true);
        ClassQ5.put("הינדואיזם", true);
        ClassQ5.put("בודהיזם", true);
        ClassQ5.put("יהדות", true);
        ClassQ5.put("סיקהיזם", true);
        ClassQ5.put("הדרוזים", true);
        ClassicQuestion q5 = new ClassicQuestion("מהם הדתות הגדולות בעולם?", "Religion", ClassQ5, false);
        classisCollection.add(q5);
        HashMap<String, Boolean> ClassQ6 = new HashMap<String, Boolean>();
        ClassQ6.put("רוסיה", true);
        ClassQ6.put("קנדה", true);
        ClassQ6.put("סין", true);
        ClassQ6.put("ארצות הברית", true);
        ClassQ6.put("ברזיל", true);
        ClassQ6.put("אוסטרליה", true);
        ClassQ6.put("הודו", true);
        ClassQ6.put("ארגנטינה", true);
        ClassQ6.put("קזחסטן", true);
        ClassQ6.put("אלג'יריה", true);
        ClassicQuestion q6 = new ClassicQuestion("מהן עשרת המדינות הגדולות בעולם לפי שטח?", "Geography", ClassQ6, false);
        classisCollection.add(q6);
        HashMap<String, Boolean> ClassQ7 = new HashMap<String, Boolean>();
        ClassQ7.put("סין", true);
        ClassQ7.put("הודו", true);
        ClassQ7.put("ארצות הברית", true);
        ClassQ7.put("אינדונזיה", true);
        ClassQ7.put("פקיסטן", true);
        ClassQ7.put("ניגריה", true);
        ClassQ7.put("ברזיל", true);
        ClassQ7.put("בנגלדש", true);
        ClassQ7.put("רוסיה", true);
        ClassQ7.put("מקסיקו", true);
        ClassicQuestion q7 = new ClassicQuestion("מהן עשרת המדינות המאוכלסות ביותר בעולם?", "Demography", ClassQ7, false);
        classisCollection.add(q7);

        HashMap<String, Boolean> ClassQ9 = new HashMap<String, Boolean>();
        ClassQ9.put("פפרוני", true);
        ClassQ9.put("זיתים", true);
        ClassQ9.put("גבינת מוצרלה", true);
        ClassQ9.put("פטריות", true);
        ClassQ9.put("בצל", true);
        ClassQ9.put("עגבניות", true);
        ClassQ9.put("פלפל חריף", true);
        ClassQ9.put("תירס", true);
        ClassQ9.put("אננס", true);
        ClassQ9.put("בצל סגול", true);
        ClassQ9.put("נקניקיות", true);
        ClassQ9.put("טונה", true);
        ClassQ9.put("בזיליקום", true);
        ClassQ9.put("צ'ילי", true);
        ClassQ9.put("רוקט", true);

        ClassicQuestion q9 = new ClassicQuestion("מהן תוספות פופולריות לפיצה?", "Food", ClassQ9, false);
        classisCollection.add(q9);
        HashMap<String, Boolean> ClassQ10 = new HashMap<String, Boolean>();
        ClassQ10.put("גיטרה", true);
        ClassQ10.put("פסנתר", true);
        ClassQ10.put("תופים", true);
        ClassQ10.put("כינור", true);
        ClassQ10.put("חליל", true);
        ClassQ10.put("טרומבון", true);
        ClassQ10.put("חצוצרה", true);
        ClassQ10.put("קונטרבס", true);
        ClassQ10.put("סקסופון", true);
        ClassQ10.put("יוקולילי", true);
        ClassQ10.put("עוד", true);
        ClassQ10.put("דרבוקה", true);
        ClassQ10.put("קלרינט", true);
        ClassQ10.put("אקורדיון", true);
        ClassQ10.put("נבל", true);

        ClassicQuestion q10 = new ClassicQuestion("מהם כלי הנגינה הפופולריים ביותר?", "Music", ClassQ10, false);
        classisCollection.add(q10);
        HashMap<String, Boolean> ClassQ11 = new HashMap<String, Boolean>();
        ClassQ11.put("טוקיו", true);
        ClassQ11.put("דלהי", true);
        ClassQ11.put("שנגחאי", true);
        ClassQ11.put("סאו פאולו", true);
        ClassQ11.put("מומבאי", true);
        ClassQ11.put("מקסיקו סיטי", true);
        ClassQ11.put("קהיר", true);
        ClassQ11.put("בייג'ינג", true);
        ClassQ11.put("אוסקה", true);
        ClassQ11.put("ניו יורק", true);
        ClassQ11.put("דאקה", true);
        ClassQ11.put("קולקטה", true);
        ClassQ11.put("לגוס", true);
        ClassQ11.put("לוס אנג'לס", true);
        ClassQ11.put("קרצ'י", true);

        ClassicQuestion q11 = new ClassicQuestion("מהן הערים הגדולות בעולם לפי אוכלוסייה?", "Geography", ClassQ11, false);
        classisCollection.add(q11);
        HashMap<String, Boolean> ClassQ12 = new HashMap<String, Boolean>();
        ClassQ12.put("אקשן", true);
        ClassQ12.put("קומדיה", true);
        ClassQ12.put("דרמה", true);
        ClassQ12.put("מדע בדיוני", true);
        ClassQ12.put("אימה", true);
        ClassQ12.put("רומנטיקה", true);
        ClassQ12.put("מתח", true);
        ClassQ12.put("אנימציה", true);
        ClassQ12.put("פנטזיה", true);
        ClassQ12.put("דוקומנטרי", true);
        ClassQ12.put("הרפתקאות", true);
        ClassQ12.put("מוזיקלי", true);
        ClassQ12.put("ספורט", true);
        ClassQ12.put("ביוגרפיה", true);
        ClassQ12.put("פשע", true);

        ClassicQuestion q12 = new ClassicQuestion("מהם ז'אנרי הסרטים המובילים?", "Entertainment", ClassQ12, false);
        classisCollection.add(q12);
        HashMap<String, Boolean> ClassQ13 = new HashMap<String, Boolean>();
        ClassQ13.put("כדורגל", true);
        ClassQ13.put("כדורסל", true);
        ClassQ13.put("טניס", true);
        ClassQ13.put("קריקט", true);
        ClassQ13.put("רוגבי", true);
        ClassQ13.put("הוקי קרח", true);
        ClassQ13.put("שחייה", true);
        ClassQ13.put("בייסבול", true);
        ClassQ13.put("פינג פונג", true);
        ClassQ13.put("אתלטיקה", true);
        ClassQ13.put("סקי", true);
        ClassQ13.put("אופניים", true);
        ClassQ13.put("התעמלות קרקע", true);
        ClassQ13.put("גולף", true);
        ClassQ13.put("ג'ודו", true);

        ClassicQuestion q13 = new ClassicQuestion("מהם ענפי הספורט הפופולריים בעולם?", "Sports", ClassQ13, false);
        classisCollection.add(q13);
        HashMap<String, Boolean> ClassQ23 = new HashMap<String, Boolean>();
        ClassQ23.put("ברדלס", true);
        ClassQ23.put("אנטילופה", true);
        ClassQ23.put("צבי", true);
        ClassQ23.put("אריה", true);
        ClassQ23.put("זברה", true);
        ClassQ23.put("טיגריס", true);
        ClassQ23.put("סוס", true);
        ClassQ23.put("גנו", true);
        ClassQ23.put("יען", true);
        ClassQ23.put("דולפין", true);
        ClassQ23.put("פומה", true);
        ClassQ23.put("זאב", true);
        ClassQ23.put("קיווית", true);
        ClassQ23.put("נמר", true);
        ClassQ23.put("ארנב", true);

        ClassicQuestion q23 = new ClassicQuestion("מהם החיות המהירות ביותר בעולם?", "Animals", ClassQ23, false);
        classisCollection.add(q23);
        HashMap<String, Boolean> ClassQ29 = new HashMap<String, Boolean>();
        ClassQ29.put("אתלטיקה", true);
        ClassQ29.put("שחייה", true);
        ClassQ29.put("התעמלות", true);
        ClassQ29.put("כדורסל", true);
        ClassQ29.put("כדורגל", true);
        ClassQ29.put("טניס", true);
        ClassQ29.put("אופניים", true);
        ClassQ29.put("קרב חמש", true);
        ClassQ29.put("קפיצה למים", true);
        ClassQ29.put("הוקי שדה", true);
        ClassQ29.put("קליעה", true);
        ClassQ29.put("ג'ודו", true);
        ClassQ29.put("איגרוף", true);
        ClassQ29.put("רכיבה", true);
        ClassQ29.put("טריאתלון", true);

        ClassicQuestion q29 = new ClassicQuestion("מהם המשחקים האולימפיים הפופולריים ביותר?", "Sports", ClassQ29, false);
        classisCollection.add(q29);
        HashMap<String, Boolean> ClassQ30 = new HashMap<String, Boolean>();
        ClassQ30.put("טלוויזיה", true);
        ClassQ30.put("מחשב", true);
        ClassQ30.put("סמארטפון", true);
        ClassQ30.put("טאבלט", true);
        ClassQ30.put("מקרר", true);
        ClassQ30.put("מיקרוגל", true);
        ClassQ30.put("מכונת כביסה", true);
        ClassQ30.put("מדיח כלים", true);
        ClassQ30.put("מזגן", true);
        ClassQ30.put("מכונת קפה", true);
        ClassQ30.put("מצלמה", true);
        ClassQ30.put("רמקול", true);
        ClassQ30.put("מאוורר", true);
        ClassQ30.put("מטען", true);
        ClassQ30.put("שעון מעורר", true);

        ClassicQuestion q30 = new ClassicQuestion("מהם המכשירים האלקטרוניים הנפוצים ביותר בבית?", "Technology", ClassQ30, false);
        classisCollection.add(q30);





        TFquestion TFQ1 = new TFquestion("ניתן לראות את נוגה מכדא" ,"Space",true,false);
        tFCollection.add(TFQ1);
        // שאלות בנושא חלל
        TFquestion TFQ2 = new TFquestion("הירח הוא הלוויין הטבעי היחיד של כדור הארץ", "Space", true, false);
        tFCollection.add(TFQ2);
        TFquestion TFQ3 = new TFquestion("שבתאי הוא הכוכב הקרוב ביותר לשמש", "Space", false, false);
        tFCollection.add(TFQ3);
        TFquestion TFQ4 = new TFquestion("ישנם תשעה כוכבי לכת במערכת השמש", "Space", false, false);
        tFCollection.add(TFQ4);
        TFquestion TFQ5 = new TFquestion("היום על מאדים נמשך יותר מ-24 שעות", "Space", true, false);
        tFCollection.add(TFQ5);

// שאלות בנושא מדע
        TFquestion TFQ6 = new TFquestion("מים רותחים ב-100 מעלות צלזיוס בגובה פני הים", "Science", true, false);
        tFCollection.add(TFQ6);
        TFquestion TFQ7 = new TFquestion("אטום מורכב מגרעין שמכיל אלקטרונים", "Science", false, false);
        tFCollection.add(TFQ7);
        TFquestion TFQ8 = new TFquestion("דינוזאורים נכחדו לפני כ-65 מיליון שנה", "Science", true, false);
        tFCollection.add(TFQ8);
        TFquestion TFQ9 = new TFquestion("האור הוא צורה של אנרגיה", "Science", true, false);
        tFCollection.add(TFQ9);
        TFquestion TFQ10 = new TFquestion("שמן כבד יותר ממים ולכן הוא שוקע בהם", "Science", false, false);
        tFCollection.add(TFQ10);

// שאלות בנושא גיאוגרפיה
        TFquestion TFQ11 = new TFquestion("הים התיכון הוא הים הגדול בעולם", "Geography", false, false);
        tFCollection.add(TFQ11);
        TFquestion TFQ12 = new TFquestion("אפריקה היא היבשת הגדולה ביותר", "Geography", false, false);
        tFCollection.add(TFQ12);
        TFquestion TFQ13 = new TFquestion("הר האוורסט הוא ההר הגבוה בעולם", "Geography", true, false);
        tFCollection.add(TFQ13);
        TFquestion TFQ14 = new TFquestion("האמזונס הוא הנהר הארוך בעולם", "Geography", false, false);
        tFCollection.add(TFQ14);
        TFquestion TFQ15 = new TFquestion("הקטבים של כדור הארץ מכוסים בעיקר קרח", "Geography", true, false);
        tFCollection.add(TFQ15);

// שאלות בנושא היסטוריה
        TFquestion TFQ16 = new TFquestion("נפוליאון היה קיסר צרפת", "History", true, false);
        tFCollection.add(TFQ16);
        TFquestion TFQ17 = new TFquestion("מלחמת העולם הראשונה התחילה בשנת 1918", "History", false, false);
        tFCollection.add(TFQ17);
        TFquestion TFQ18 = new TFquestion("המהפכה התעשייתית החלה בבריטניה", "History", true, false);
        tFCollection.add(TFQ18);
        TFquestion TFQ19 = new TFquestion("מדינת ישראל הוקמה בשנת 1947", "History", false, false);
        tFCollection.add(TFQ19);
        TFquestion TFQ20 = new TFquestion("המגנה כרטה נחתמה בשנת 1215", "History", true, false);
        tFCollection.add(TFQ20);

// שאלות כלליות
        TFquestion TFQ21 = new TFquestion("חתולים רואים טוב יותר בלילה מאשר ביום", "General", true, false);
        tFCollection.add(TFQ21);
        TFquestion TFQ22 = new TFquestion("סוכר נמס במים", "General", true, false);
        tFCollection.add(TFQ22);
        TFquestion TFQ23 = new TFquestion("הדגל של בריטניה הוא בצבעים ירוק, לבן ושחור", "General", false, false);
        tFCollection.add(TFQ23);
        TFquestion TFQ24 = new TFquestion("רוחב הפס הוא מדד למהירות האינטרנט", "General", true, false);
        tFCollection.add(TFQ24);
        TFquestion TFQ25 = new TFquestion("ציפורים הן יונקים", "General", false, false);
        tFCollection.add(TFQ25);

    }

    public ArrayList<ClassicQuestion> getClassisCollection() {
        return classisCollection;
    }

    public ArrayList<TFquestion> getTFCollection() {
        return tFCollection;
    }
}

