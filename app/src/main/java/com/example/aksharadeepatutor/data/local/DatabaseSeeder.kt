package com.example.aksharadeepatutor.data.local

import com.example.aksharadeepatutor.data.local.entities.ChapterEntity
import com.example.aksharadeepatutor.data.local.entities.QuestionEntity
import com.example.aksharadeepatutor.data.local.entities.SubjectEntity

object DatabaseSeeder {

    // ---------------- SUBJECTS ----------------
    fun getSubjects(): List<SubjectEntity> {
        return listOf(
            SubjectEntity(id = 1, name = "Mathematics"),
            SubjectEntity(id = 2, name = "Science"),
            SubjectEntity(id = 3, name = "Social Science")
        )
    }

    // ---------------- CHAPTERS ----------------
    fun getChapters(): List<ChapterEntity> {
        return listOf(
            // Mathematics
            ChapterEntity(id = 1, subjectId = 1, name = "Arithmetic Progressions"),
            ChapterEntity(id = 2, subjectId = 1, name = "Triangles"),
            ChapterEntity(id = 3, subjectId = 1, name = "Circles"),
            ChapterEntity(id = 4, subjectId = 1, name = "Trigonometry"),
            ChapterEntity(id = 5, subjectId = 1, name = "Statistics"),

            // Science
            ChapterEntity(id = 6, subjectId = 2, name = "Chemical Reactions"),
            ChapterEntity(id = 7, subjectId = 2, name = "Acids Bases and Salts"),
            ChapterEntity(id = 8, subjectId = 2, name = "Metals and Non-metals"),
            ChapterEntity(id = 9, subjectId = 2, name = "Life Processes"),
            ChapterEntity(id = 10, subjectId = 2, name = "Electricity"),

            // Social Science
            ChapterEntity(id = 11, subjectId = 3, name = "Advent of Europeans"),
            ChapterEntity(id = 12, subjectId = 3, name = "Extension of British Rule"),
            ChapterEntity(id = 13, subjectId = 3, name = "Impact of British Rule"),
            ChapterEntity(id = 14, subjectId = 3, name = "Indian Freedom Struggle"),
            ChapterEntity(id = 15, subjectId = 3, name = "Constitution of India")
        )
    }

    // ---------------- QUESTIONS (PART 1 ONLY) ----------------
    fun getQuestions(): List<QuestionEntity> {
        val questions = mutableListOf<QuestionEntity>()

        // ================= CHAPTER 1: ARITHMETIC PROGRESSIONS =================
        questions.add(
            QuestionEntity(
                chapterId = 1,
                questionText = "In an AP, if d = -4, n = 7, an = 4, then a is",
                optionA = "6",
                optionB = "7",
                optionC = "20",
                optionD = "28",
                correctAnswerIndex = 3,
                explanation = "Using AP formula an = a + (n-1)d, solving gives a = 28."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 1,
                questionText = "The first term of an AP is 5 and common difference is -3. The 10th term is",
                optionA = "32",
                optionB = "-22",
                optionC = "22",
                optionD = "-32",
                correctAnswerIndex = 1,
                explanation = "Apply an = a + (n-1)d → 5 + 9(-3) = -22."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 1,
                questionText = "The nth term of AP is an = 3 + 4n. The common difference is",
                optionA = "7",
                optionB = "3",
                optionC = "4",
                optionD = "1",
                correctAnswerIndex = 2,
                explanation = "Coefficient of n is 4, so common difference = 4."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 1,
                questionText = "Sum of first n terms of AP is",
                optionA = "n/2[2a+(n-1)d]",
                optionB = "n[2a+(n-1)d]",
                optionC = "n/2[a+(n-1)d]",
                optionD = "n[a+d]",
                correctAnswerIndex = 0,
                explanation = "Standard AP sum formula is S = n/2[2a+(n-1)d]."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 1,
                questionText = "Common difference of 1, -1, -3, -5 is",
                optionA = "2",
                optionB = "-2",
                optionC = "0",
                optionD = "1",
                correctAnswerIndex = 1,
                explanation = "Each term decreases by 2, so d = -2."
            )
        )

        // ================= CHAPTER 2: TRIANGLES =================
        questions.add(
            QuestionEntity(
                chapterId = 2,
                questionText = "All equilateral triangles are",
                optionA = "Congruent",
                optionB = "Similar",
                optionC = "Both",
                optionD = "None",
                correctAnswerIndex = 1,
                explanation = "All equilateral triangles have equal angles, so they are similar."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 2,
                questionText = "Pythagoras theorem applies to",
                optionA = "Any triangle",
                optionB = "Right triangle",
                optionC = "Equilateral triangle",
                optionD = "Isosceles triangle",
                correctAnswerIndex = 1,
                explanation = "It applies only to right-angled triangles."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 2,
                questionText = "Area ratio of similar triangles is equal to",
                optionA = "Ratio of sides",
                optionB = "Ratio of heights",
                optionC = "Square of ratio of sides",
                optionD = "Cube of ratio of sides",
                correctAnswerIndex = 2,
                explanation = "Area ratio = square of corresponding sides ratio."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 2,
                questionText = "If triangles are similar, then corresponding sides are",
                optionA = "Equal",
                optionB = "Proportional",
                optionC = "Parallel",
                optionD = "Perpendicular",
                correctAnswerIndex = 1,
                explanation = "Corresponding sides of similar triangles are proportional."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 2,
                questionText = "A 3-4-5 triangle is",
                optionA = "Acute",
                optionB = "Obtuse",
                optionC = "Right angled",
                optionD = "Equilateral",
                correctAnswerIndex = 2,
                explanation = "3² + 4² = 5², so it is a right triangle."
            )
        )

        // ================= CHAPTER 3: CIRCLES =================
        questions.add(
            QuestionEntity(
                chapterId = 3,
                questionText = "A tangent touches a circle at",
                optionA = "Two points",
                optionB = "One point",
                optionC = "No point",
                optionD = "Infinite points",
                correctAnswerIndex = 1,
                explanation = "A tangent touches circle at exactly one point."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 3,
                questionText = "A line cutting circle at two points is called",
                optionA = "Tangent",
                optionB = "Secant",
                optionC = "Radius",
                optionD = "Chord",
                correctAnswerIndex = 1,
                explanation = "Secant intersects circle at two points."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 3,
                questionText = "Length of tangents from external point are",
                optionA = "Unequal",
                optionB = "Equal",
                optionC = "Parallel",
                optionD = "Zero",
                correctAnswerIndex = 1,
                explanation = "Tangents from same external point are equal."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 3,
                questionText = "Tangent is perpendicular to",
                optionA = "Chord",
                optionB = "Radius",
                optionC = "Diameter",
                optionD = "Secant",
                correctAnswerIndex = 1,
                explanation = "Tangent is perpendicular to radius at point of contact."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 3,
                questionText = "Distance between parallel tangents of radius 4 cm circle is",
                optionA = "4 cm",
                optionB = "8 cm",
                optionC = "2 cm",
                optionD = "6 cm",
                correctAnswerIndex = 1,
                explanation = "Distance between parallel tangents = 2 × radius = 8 cm"
            )
        )

        // ================= CHAPTER 4: TRIGONOMETRY =================
        questions.add(
            QuestionEntity(
                chapterId = 4,
                questionText = "The value of sin 60° cos 30° + sin 30° cos 60° is",
                optionA = "0",
                optionB = "1",
                optionC = "2",
                optionD = "1/2",
                correctAnswerIndex = 1,
                explanation = "Using identity sin(A+B) = sin A cos B + cos A sin B = sin 90° = 1"
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 4,
                questionText = "2 tan 30° / (1 + tan² 30°) equals",
                optionA = "sin 60°",
                optionB = "cos 60°",
                optionC = "tan 60°",
                optionD = "sin 30°",
                correctAnswerIndex = 0,
                explanation = "Using identity for sin 2A = 2tanA/(1+tan²A)"
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 4,
                questionText = "If tan A = 4/3, then sin A is",
                optionA = "3/4",
                optionB = "3/5",
                optionC = "4/5",
                optionD = "5/4",
                correctAnswerIndex = 1,
                explanation = "Using Pythagoras: hypotenuse = 5, so sin A = 3/5"
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 4,
                questionText = "sin(90° - A) equals",
                optionA = "sin A",
                optionB = "cos A",
                optionC = "tan A",
                optionD = "cot A",
                correctAnswerIndex = 1,
                explanation = "Co-function identity: sin(90°-A) = cos A"
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 4,
                questionText = "The value of sin θ increases from 0° to",
                optionA = "45°",
                optionB = "60°",
                optionC = "90°",
                optionD = "180°",
                correctAnswerIndex = 2,
                explanation = "sin θ increases in first quadrant and becomes maximum at 90°"
            )
        )

        // ================= CHAPTER 5: STATISTICS =================
        questions.add(
            QuestionEntity(
                chapterId = 5,
                questionText = "Class mark of 10–25 is",
                optionA = "15",
                optionB = "17.5",
                optionC = "35",
                optionD = "25",
                correctAnswerIndex = 1,
                explanation = "Class mark = (10 + 25) / 2 = 17.5"
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 5,
                questionText = "Which is NOT a measure of central tendency?",
                optionA = "Mean",
                optionB = "Median",
                optionC = "Range",
                optionD = "Mode",
                correctAnswerIndex = 2,
                explanation = "Range is a measure of dispersion, not central tendency"
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 5,
                questionText = "Mode of 2, 3, 3, 5, 5, 5, 7, 8 is",
                optionA = "3",
                optionB = "5",
                optionC = "8",
                optionD = "7",
                correctAnswerIndex = 1,
                explanation = "5 appears most frequently"
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 5,
                questionText = "Mean of first five natural numbers is",
                optionA = "3",
                optionB = "5",
                optionC = "2.5",
                optionD = "4",
                correctAnswerIndex = 0,
                explanation = "(1+2+3+4+5)/5 = 3"
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 5,
                questionText = "Median of 7, 8, 9, 10, 11, 12, 13 is",
                optionA = "11",
                optionB = "10",
                optionC = "9",
                optionD = "12",
                correctAnswerIndex = 1,
                explanation = "Middle value of ordered data is 10"
            )
        )

        // =======================
// CHAPTER 6 - CHEMICAL REACTIONS (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 6,
                questionText = "The reaction in which two or more substances combine to form a single product is called",
                optionA = "Decomposition",
                optionB = "Combination",
                optionC = "Displacement",
                optionD = "Redox",
                correctAnswerIndex = 1,
                explanation = "Combination reaction forms a single product from multiple reactants."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 6,
                questionText = "Respiration is an example of",
                optionA = "Endothermic reaction",
                optionB = "Exothermic reaction",
                optionC = "Photochemical reaction",
                optionD = "Neutralization",
                correctAnswerIndex = 1,
                explanation = "Respiration releases energy, so it is exothermic."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 6,
                questionText = "Reaction CaCO3 → CaO + CO2 is an example of",
                optionA = "Combination",
                optionB = "Decomposition",
                optionC = "Displacement",
                optionD = "Double displacement",
                correctAnswerIndex = 1,
                explanation = "One compound breaks into simpler substances, so it is decomposition."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 6,
                questionText = "A substance that gains oxygen is said to be",
                optionA = "Reduced",
                optionB = "Oxidised",
                optionC = "Neutralised",
                optionD = "Hydrolysed",
                correctAnswerIndex = 1,
                explanation = "Oxidation involves gain of oxygen."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 6,
                questionText = "Rusting of iron is an example of",
                optionA = "Rapid reaction",
                optionB = "Slow reaction",
                optionC = "Explosion",
                optionD = "Physical change",
                correctAnswerIndex = 1,
                explanation = "Rusting occurs slowly over time due to oxidation."
            )
        )


// =======================
// CHAPTER 7 - ACIDS, BASES AND SALTS (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 7,
                questionText = "Acids turn blue litmus paper into",
                optionA = "Green",
                optionB = "Red",
                optionC = "Yellow",
                optionD = "Pink",
                correctAnswerIndex = 1,
                explanation = "Acids turn blue litmus red."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 7,
                questionText = "The pH of a neutral solution is",
                optionA = "0",
                optionB = "7",
                optionC = "14",
                optionD = "1",
                correctAnswerIndex = 1,
                explanation = "Neutral solutions have pH equal to 7."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 7,
                questionText = "Baking soda is chemically known as",
                optionA = "Na2CO3",
                optionB = "NaHCO3",
                optionC = "NaOH",
                optionD = "CaCO3",
                correctAnswerIndex = 1,
                explanation = "Baking soda is sodium hydrogen carbonate (NaHCO3)."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 7,
                questionText = "Bleaching powder is",
                optionA = "CaOCl2",
                optionB = "NaCl",
                optionC = "CaCO3",
                optionD = "Na2CO3",
                correctAnswerIndex = 0,
                explanation = "Bleaching powder chemical formula is CaOCl2."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 7,
                questionText = "Phenolphthalein in basic solution turns",
                optionA = "Colorless",
                optionB = "Pink",
                optionC = "Red",
                optionD = "Blue",
                correctAnswerIndex = 1,
                explanation = "Phenolphthalein turns pink in basic medium."
            )
        )


// =======================
// CHAPTER 8 - METALS AND NON-METALS (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 8,
                questionText = "Which metal is liquid at room temperature?",
                optionA = "Iron",
                optionB = "Mercury",
                optionC = "Copper",
                optionD = "Aluminium",
                correctAnswerIndex = 1,
                explanation = "Mercury is the only metal that is liquid at room temperature."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 8,
                questionText = "The ability of metals to be beaten into sheets is called",
                optionA = "Ductility",
                optionB = "Malleability",
                optionC = "Conductivity",
                optionD = "Sonority",
                correctAnswerIndex = 1,
                explanation = "Malleability is the property of forming thin sheets."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 8,
                questionText = "Which non-metal is good conductor of electricity?",
                optionA = "Sulphur",
                optionB = "Graphite",
                optionC = "Oxygen",
                optionD = "Nitrogen",
                correctAnswerIndex = 1,
                explanation = "Graphite is a good conductor of electricity."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 8,
                questionText = "Metals react with acids to produce",
                optionA = "Oxygen",
                optionB = "Hydrogen gas",
                optionC = "Carbon dioxide",
                optionD = "Nitrogen",
                correctAnswerIndex = 1,
                explanation = "Metals + acid → salt + hydrogen gas."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 8,
                questionText = "Galvanization is done to protect iron from",
                optionA = "Melting",
                optionB = "Rusting",
                optionC = "Breaking",
                optionD = "Corrosion only in air",
                correctAnswerIndex = 1,
                explanation = "Zinc coating prevents rusting of iron."
            )
        )

        // =======================
// CHAPTER 9 - LIFE PROCESSES (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 9,
                questionText = "The mode of nutrition in Amoeba is",
                optionA = "Autotrophic",
                optionB = "Holozoic",
                optionC = "Saprophytic",
                optionD = "Parasitic",
                correctAnswerIndex = 1,
                explanation = "Amoeba engulfs food particles, so it shows holozoic nutrition."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 9,
                questionText = "The enzyme present in saliva is",
                optionA = "Pepsin",
                optionB = "Trypsin",
                optionC = "Amylase",
                optionD = "Lipase",
                correctAnswerIndex = 2,
                explanation = "Salivary amylase breaks down starch into sugar."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 9,
                questionText = "The respiratory pigment in human blood is",
                optionA = "Chlorophyll",
                optionB = "Haemoglobin",
                optionC = "Carotene",
                optionD = "Melanin",
                correctAnswerIndex = 1,
                explanation = "Haemoglobin carries oxygen in blood."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 9,
                questionText = "The functional unit of kidney is",
                optionA = "Neuron",
                optionB = "Nephron",
                optionC = "Alveoli",
                optionD = "Axon",
                correctAnswerIndex = 1,
                explanation = "Nephron filters blood and forms urine."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 9,
                questionText = "Xylem in plants is responsible for",
                optionA = "Transport of food",
                optionB = "Transport of water",
                optionC = "Transport of oxygen",
                optionD = "Transport of hormones",
                correctAnswerIndex = 1,
                explanation = "Xylem carries water and minerals from roots to leaves."
            )
        )


// =======================
// CHAPTER 10 - ELECTRICITY (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 10,
                questionText = "The SI unit of electric current is",
                optionA = "Volt",
                optionB = "Ampere",
                optionC = "Ohm",
                optionD = "Watt",
                correctAnswerIndex = 1,
                explanation = "Electric current is measured in ampere."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 10,
                questionText = "Ohm’s law states that V is proportional to",
                optionA = "Resistance",
                optionB = "Current",
                optionC = "Power",
                optionD = "Energy",
                correctAnswerIndex = 1,
                explanation = "V ∝ I when temperature remains constant."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 10,
                questionText = "The device used to measure current is",
                optionA = "Voltmeter",
                optionB = "Ammeter",
                optionC = "Galvanometer",
                optionD = "Barometer",
                correctAnswerIndex = 1,
                explanation = "Ammeter measures electric current in a circuit."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 10,
                questionText = "In a series circuit, current is",
                optionA = "Different in each component",
                optionB = "Same everywhere",
                optionC = "Zero",
                optionD = "Maximum at battery only",
                correctAnswerIndex = 1,
                explanation = "Current remains same in series connection."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 10,
                questionText = "The SI unit of power is",
                optionA = "Joule",
                optionB = "Watt",
                optionC = "Volt",
                optionD = "Ampere",
                correctAnswerIndex = 1,
                explanation = "Power is measured in watts."
            )
        )
// =======================
// CHAPTER 11 - ADVENT OF EUROPEANS (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 11,
                questionText = "Who discovered the sea route to India in 1498?",
                optionA = "Christopher Columbus",
                optionB = "Vasco da Gama",
                optionC = "Marco Polo",
                optionD = "Amerigo Vespucci",
                correctAnswerIndex = 1,
                explanation = "Vasco da Gama reached Calicut in 1498 via sea route."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 11,
                questionText = "The French settlement in India was mainly at",
                optionA = "Goa",
                optionB = "Pondicherry",
                optionC = "Calcutta",
                optionD = "Bombay",
                correctAnswerIndex = 1,
                explanation = "Pondicherry was the main French colony in India."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 11,
                questionText = "The Battle of Plassey was fought in",
                optionA = "1757",
                optionB = "1764",
                optionC = "1857",
                optionD = "1776",
                correctAnswerIndex = 0,
                explanation = "Battle of Plassey was fought in 1757."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 11,
                questionText = "The English East India Company was established in",
                optionA = "1600",
                optionB = "1757",
                optionC = "1498",
                optionD = "1857",
                correctAnswerIndex = 0,
                explanation = "East India Company was formed in 1600."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 11,
                questionText = "The Battle of Buxar was fought in",
                optionA = "1757",
                optionB = "1764",
                optionC = "1857",
                optionD = "1799",
                correctAnswerIndex = 1,
                explanation = "Battle of Buxar happened in 1764."
            )
        )


// =======================
// CHAPTER 12 - EXTENSION OF BRITISH RULE (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 12,
                questionText = "The Doctrine of Lapse was introduced by",
                optionA = "Lord Wellesley",
                optionB = "Lord Dalhousie",
                optionC = "Lord Cornwallis",
                optionD = "Lord Canning",
                correctAnswerIndex = 1,
                explanation = "Lord Dalhousie introduced Doctrine of Lapse."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 12,
                questionText = "The Subsidiary Alliance was introduced by",
                optionA = "Lord Wellesley",
                optionB = "Lord Dalhousie",
                optionC = "Robert Clive",
                optionD = "Lord Ripon",
                correctAnswerIndex = 0,
                explanation = "Lord Wellesley introduced Subsidiary Alliance system."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 12,
                questionText = "Tipu Sultan died in which war?",
                optionA = "First Anglo-Mysore War",
                optionB = "Second Anglo-Mysore War",
                optionC = "Third Anglo-Mysore War",
                optionD = "Fourth Anglo-Mysore War",
                correctAnswerIndex = 3,
                explanation = "Tipu Sultan died in the Fourth Anglo-Mysore War (1799)."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 12,
                questionText = "The Treaty of Bassein was signed with",
                optionA = "Marathas",
                optionB = "Sikhs",
                optionC = "British",
                optionD = "French",
                correctAnswerIndex = 0,
                explanation = "Treaty of Bassein was signed between British and Maratha Peshwa."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 12,
                questionText = "Ranjit Singh was the ruler of",
                optionA = "Bengal",
                optionB = "Punjab",
                optionC = "Mysore",
                optionD = "Awadh",
                correctAnswerIndex = 1,
                explanation = "Ranjit Singh was the ruler of Punjab."
            )
        )


// =======================
// CHAPTER 13 - IMPACT OF BRITISH RULE (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 13,
                questionText = "Permanent Settlement was introduced by",
                optionA = "Lord Cornwallis",
                optionB = "Lord Dalhousie",
                optionC = "Warren Hastings",
                optionD = "Lord Wellesley",
                correctAnswerIndex = 0,
                explanation = "Lord Cornwallis introduced Permanent Settlement in Bengal."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 13,
                questionText = "Ryotwari system was introduced in",
                optionA = "Bengal",
                optionB = "Madras",
                optionC = "Punjab",
                optionD = "Delhi",
                correctAnswerIndex = 1,
                explanation = "Ryotwari system was first introduced in Madras."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 13,
                questionText = "Sati system was abolished in 1829 by",
                optionA = "Lord Dalhousie",
                optionB = "Lord William Bentinck",
                optionC = "Lord Ripon",
                optionD = "Lord Canning",
                correctAnswerIndex = 1,
                explanation = "Lord William Bentinck abolished Sati system."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 13,
                questionText = "English Education Act was introduced in",
                optionA = "1835",
                optionB = "1857",
                optionC = "1905",
                optionD = "1947",
                correctAnswerIndex = 0,
                explanation = "Macaulay’s education policy was introduced in 1835."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 13,
                questionText = "The first university in India was established in",
                optionA = "1857",
                optionB = "1947",
                optionC = "1901",
                optionD = "1885",
                correctAnswerIndex = 0,
                explanation = "First universities were established in 1857 in Calcutta, Bombay, Madras."
            )
        )

        // =======================
// CHAPTER 14 - INDIAN FREEDOM STRUGGLE (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 14,
                questionText = "The First War of Indian Independence took place in",
                optionA = "1857",
                optionB = "1942",
                optionC = "1920",
                optionD = "1947",
                correctAnswerIndex = 0,
                explanation = "The Revolt of 1857 is considered the First War of Independence."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 14,
                questionText = "The Non-Cooperation Movement was launched in",
                optionA = "1919",
                optionB = "1920",
                optionC = "1930",
                optionD = "1942",
                correctAnswerIndex = 1,
                explanation = "Mahatma Gandhi launched it in 1920."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 14,
                questionText = "The Jallianwala Bagh massacre took place in",
                optionA = "1917",
                optionB = "1919",
                optionC = "1922",
                optionD = "1930",
                correctAnswerIndex = 1,
                explanation = "It happened in Amritsar in 1919."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 14,
                questionText = "The Quit India Movement was started in",
                optionA = "1940",
                optionB = "1942",
                optionC = "1945",
                optionD = "1947",
                correctAnswerIndex = 1,
                explanation = "Quit India Movement was launched in 1942."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 14,
                questionText = "Who gave the slogan 'Do or Die'?",
                optionA = "Jawaharlal Nehru",
                optionB = "Subhash Chandra Bose",
                optionC = "Mahatma Gandhi",
                optionD = "Bhagat Singh",
                correctAnswerIndex = 2,
                explanation = "Mahatma Gandhi gave the slogan 'Do or Die'."
            )
        )


// =======================
// CHAPTER 15 - CONSTITUTION OF INDIA (5 QUESTIONS)
// =======================

        questions.add(
            QuestionEntity(
                chapterId = 15,
                questionText = "The Indian Constitution came into force on",
                optionA = "15 August 1947",
                optionB = "26 January 1950",
                optionC = "26 November 1949",
                optionD = "2 October 1948",
                correctAnswerIndex = 1,
                explanation = "Indian Constitution came into effect on 26 January 1950."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 15,
                questionText = "The Drafting Committee was chaired by",
                optionA = "Dr. Rajendra Prasad",
                optionB = "Dr. B.R. Ambedkar",
                optionC = "Jawaharlal Nehru",
                optionD = "Sardar Patel",
                correctAnswerIndex = 1,
                explanation = "Dr. B.R. Ambedkar was the chairman of Drafting Committee."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 15,
                questionText = "The Fundamental Rights are included in",
                optionA = "Part II",
                optionB = "Part III",
                optionC = "Part IV",
                optionD = "Part V",
                correctAnswerIndex = 1,
                explanation = "Fundamental Rights are in Part III of Constitution."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 15,
                questionText = "How many Fundamental Duties are there in Indian Constitution?",
                optionA = "10",
                optionB = "11",
                optionC = "12",
                optionD = "9",
                correctAnswerIndex = 1,
                explanation = "There are 11 Fundamental Duties."
            )
        )

        questions.add(
            QuestionEntity(
                chapterId = 15,
                questionText = "Article 32 is known as",
                optionA = "Right to Equality",
                optionB = "Right to Education",
                optionC = "Right to Constitutional Remedies",
                optionD = "Right to Freedom",
                correctAnswerIndex = 2,
                explanation = "Article 32 protects constitutional remedies and is called the heart of the Constitution."
            )
        )
        return questions
    }
}
