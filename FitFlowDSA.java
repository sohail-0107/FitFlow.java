import java.util.*;

// ============================================================
//   FITFLOW DSA — Java Terminal App
//   CO1: Sorting + Binary Search
//   CO2: Singly Linked List (Activity Log)
//   CO3: Stack, Queue, Max-Heap
//   CO4: HashMap (Meal Calorie Lookup)
// ============================================================

public class FitFlowDSA {

    static Scanner sc = new Scanner(System.in);
    static String currentUser = "";

    // ── User profile ─────────────────────────────────────────
    static double weight = 0, height = 0;
    static int age = 0;
    static String goal = "";

    public static void main(String[] args) {
        printBanner();
        setupProfile();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> showBMI();
                case 2 -> co1Menu();
                case 3 -> co2Menu();
                case 4 -> co3Menu();
                case 5 -> co4Menu();
                case 0 -> { System.out.println("\n  Goodbye, " + currentUser + "! Stay fit! 💪\n"); running = false; }
                default -> System.out.println("  ❌ Invalid choice.");
            }
        }
    }

    // ════════════════════════════════════════════════════════
    //  SETUP
    // ════════════════════════════════════════════════════════
    static void printBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║       FITFLOW DSA — Java Edition         ║");
        System.out.println("  ║  CO1 · CO2 · CO3 · CO4  All-in-one      ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.println();
    }

    static void setupProfile() {
        System.out.print("  Enter your name: ");
        currentUser = sc.nextLine().trim();
        System.out.print("  Age: ");
        age = Integer.parseInt(sc.nextLine().trim());
        System.out.print("  Weight (kg): ");
        weight = Double.parseDouble(sc.nextLine().trim());
        System.out.print("  Height (cm): ");
        height = Double.parseDouble(sc.nextLine().trim());
        System.out.println("  Goal (lose / maintain / gain): ");
        System.out.print("  > ");
        goal = sc.nextLine().trim().toLowerCase();
        System.out.println("\n  ✅ Welcome, " + currentUser + "!\n");
    }

    static void printMainMenu() {
        double bmi = calcBMI();
        System.out.println("\n  ┌─────────────────────────────────────┐");
        System.out.printf ("  │  👤 %-15s  BMI: %-6.1f      │%n", currentUser, bmi);
        System.out.println("  ├─────────────────────────────────────┤");
        System.out.println("  │  1. 📊 View BMI & Profile           │");
        System.out.println("  │  2. ⏱  CO1 — Sorting & Search       │");
        System.out.println("  │  3. 🔗 CO2 — Linked List Log        │");
        System.out.println("  │  4. 📚 CO3 — Stack / Queue / Heap   │");
        System.out.println("  │  5. 🗂  CO4 — HashMap Meal Lookup   │");
        System.out.println("  │  0. 🚪 Exit                         │");
        System.out.println("  └─────────────────────────────────────┘");
    }

    static double calcBMI() {
        double hm = height / 100.0;
        return Math.round((weight / (hm * hm)) * 10.0) / 10.0;
    }

    static void showBMI() {
        double bmi = calcBMI();
        String status = bmi < 18.5 ? "Underweight" : bmi < 25.0 ? "Normal" : bmi < 30.0 ? "Overweight" : "Obese";
        int cal = goal.equals("gain") || bmi < 18.5 ? 2600
                : goal.equals("lose") || bmi >= 25   ? 1900 : 2400;

        System.out.println("\n  ╔═════════ BMI REPORT ═════════╗");
        System.out.printf ("  ║  Name   : %-18s ║%n", currentUser);
        System.out.printf ("  ║  Age    : %-18d ║%n", age);
        System.out.printf ("  ║  Weight : %-15.1f kg  ║%n", weight);
        System.out.printf ("  ║  Height : %-15.1f cm  ║%n", height);
        System.out.printf ("  ║  BMI    : %-18.1f ║%n", bmi);
        System.out.printf ("  ║  Status : %-18s ║%n", status);
        System.out.printf ("  ║  Goal   : %-18s ║%n", goal);
        System.out.printf ("  ║  Daily Calories : %-10d ║%n", cal);
        System.out.println("  ╚══════════════════════════════╝");
    }

    // ════════════════════════════════════════════════════════
    //  CO1 — SORTING & BINARY SEARCH
    // ════════════════════════════════════════════════════════

    static void co1Menu() {
        boolean back = false;
        // Sample workout calorie data
        int[] data = {320, 150, 480, 90, 260, 410, 55, 370, 200, 130, 500, 75};

        while (!back) {
            System.out.println("\n  ── CO1: Sorting & Search ──────────────");
            System.out.println("  Data (workout calories): " + Arrays.toString(data));
            System.out.println("  [1] Bubble Sort    O(n²)");
            System.out.println("  [2] Selection Sort O(n²)");
            System.out.println("  [3] Insertion Sort O(n²)");
            System.out.println("  [4] Merge Sort     O(n log n)");
            System.out.println("  [5] Quick Sort     O(n log n)");
            System.out.println("  [6] Binary Search  O(log n)  [needs sorted]");
            System.out.println("  [7] Linear Search  O(n)");
            System.out.println("  [8] Shuffle / reset data");
            System.out.println("  [0] Back");
            int ch = readInt("  > ");

            switch (ch) {
                case 1 -> { data = bubbleSort(data.clone()); printSorted("Bubble Sort", data); }
                case 2 -> { data = selectionSort(data.clone()); printSorted("Selection Sort", data); }
                case 3 -> { data = insertionSort(data.clone()); printSorted("Insertion Sort", data); }
                case 4 -> { data = mergeSort(data.clone()); printSorted("Merge Sort", data); }
                case 5 -> { data = quickSortWrap(data.clone()); printSorted("Quick Sort", data); }
                case 6 -> binarySearchMenu(data);
                case 7 -> linearSearchMenu(data);
                case 8 -> { data = new int[]{320,150,480,90,260,410,55,370,200,130,500,75}; System.out.println("  🔀 Data reset."); }
                case 0 -> back = true;
                default -> System.out.println("  ❌ Invalid.");
            }
        }
    }

    // ── Bubble Sort ──────────────────────────────────────────
    // Best: O(n) | Avg/Worst: O(n²) | Space: O(1) | Stable
    static int[] bubbleSort(int[] arr) {
        int n = arr.length, swaps = 0, comparisons = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j]; arr[j] = arr[j+1]; arr[j+1] = tmp;
                    swaps++; swapped = true;
                }
            }
            if (!swapped) break; // Early exit: already sorted → O(n)
        }
        System.out.printf("  📊 Bubble Sort: %d comparisons, %d swaps%n", comparisons, swaps);
        return arr;
    }

    // ── Selection Sort ───────────────────────────────────────
    // Best/Avg/Worst: O(n²) | Space: O(1) | Not Stable
    static int[] selectionSort(int[] arr) {
        int n = arr.length, swaps = 0, comparisons = 0;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) { comparisons++; if (arr[j] < arr[minIdx]) minIdx = j; }
            if (minIdx != i) { int tmp = arr[i]; arr[i] = arr[minIdx]; arr[minIdx] = tmp; swaps++; }
        }
        System.out.printf("  📊 Selection Sort: %d comparisons, %d swaps%n", comparisons, swaps);
        return arr;
    }

    // ── Insertion Sort ───────────────────────────────────────
    // Best: O(n) nearly-sorted | Avg/Worst: O(n²) | Space: O(1) | Stable
    static int[] insertionSort(int[] arr) {
        int n = arr.length, shifts = 0, comparisons = 0;
        for (int i = 1; i < n; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) { comparisons++; arr[j + 1] = arr[j]; j--; shifts++; }
            arr[j + 1] = key;
        }
        System.out.printf("  📊 Insertion Sort: %d comparisons, %d shifts%n", comparisons, shifts);
        return arr;
    }

    // ── Merge Sort ───────────────────────────────────────────
    // T(n) = 2T(n/2) + O(n) → O(n log n) [Master Theorem Case 2]
    // All cases: O(n log n) | Space: O(n) | Stable
    static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        int[] left  = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left, right);
    }

    static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            result[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        while (i < left.length)  result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }

    // ── Quick Sort ────────────────────────────────────────────
    // T(n) = T(k) + T(n-k-1) + O(n)
    // Best/Avg: O(n log n) | Worst: O(n²) bad pivot | Space: O(log n)
    static int[] quickSortWrap(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    static void quickSort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int p = partition(arr, lo, hi);
            quickSort(arr, lo, p - 1);
            quickSort(arr, p + 1, hi);
        }
    }

    static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[hi], i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }
        int tmp = arr[i+1]; arr[i+1] = arr[hi]; arr[hi] = tmp;
        return i + 1;
    }

    static void printSorted(String name, int[] arr) {
        System.out.println("  ✅ " + name + " result: " + Arrays.toString(arr));
        printComplexity(name);
    }

    static void printComplexity(String name) {
        System.out.println("  ┌──────────────────────────────────────────────────┐");
        switch (name) {
            case "Bubble Sort"    -> System.out.println("  │ Ω(n)  Θ(n²)  O(n²)  Space O(1)  Stable ✅       │");
            case "Selection Sort" -> System.out.println("  │ Ω(n²) Θ(n²)  O(n²)  Space O(1)  Not Stable ❌   │");
            case "Insertion Sort" -> System.out.println("  │ Ω(n)  Θ(n²)  O(n²)  Space O(1)  Stable ✅       │");
            case "Merge Sort"     -> System.out.println("  │ Ω(n log n) Θ(n log n) O(n log n) Space O(n) ✅  │");
            case "Quick Sort"     -> System.out.println("  │ Ω(n log n) Θ(n log n) O(n²)  Space O(log n) ❌  │");
        }
        System.out.println("  └──────────────────────────────────────────────────┘");
    }

    // ── Binary Search ─────────────────────────────────────────
    // Precondition: array must be sorted | O(log n) | Recurrence: T(n)=T(n/2)+O(1)
    static void binarySearchMenu(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        System.out.println("  Sorted array: " + Arrays.toString(sorted));
        int target = readInt("  Enter calorie value to search: ");
        int lo = 0, hi = sorted.length - 1, steps = 0;
        System.out.println("  --- Binary Search Steps ---");
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            steps++;
            System.out.printf("  Step %d: lo=%d hi=%d mid=%d → arr[mid]=%d%n", steps, lo, hi, mid, sorted[mid]);
            if (sorted[mid] == target) {
                System.out.printf("  ✅ Found %d at index %d in %d step(s). O(log n) = O(log %d) ≈ %d%n",
                        target, mid, steps, sorted.length, (int)(Math.log(sorted.length)/Math.log(2))+1);
                return;
            } else if (sorted[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        System.out.printf("  ❌ %d not found in %d step(s).%n", target, steps);
    }

    // ── Linear Search ─────────────────────────────────────────
    // O(n) | No precondition | Recurrence: T(n)=T(n-1)+O(1)
    static void linearSearchMenu(int[] arr) {
        System.out.println("  Array: " + Arrays.toString(arr));
        int target = readInt("  Enter calorie value to search: ");
        System.out.println("  --- Linear Search Steps ---");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("  Step %d: arr[%d]=%d%s%n", i+1, i, arr[i], arr[i]==target ? " ← FOUND!" : "");
            if (arr[i] == target) {
                System.out.printf("  ✅ Found %d at index %d in %d step(s). O(n) — scanned %d elements.%n",
                        target, i, i+1, i+1);
                return;
            }
        }
        System.out.printf("  ❌ %d not found after %d steps. Worst case O(n)=%d.%n", target, arr.length, arr.length);
    }

    // ════════════════════════════════════════════════════════
    //  CO2 — SINGLY LINKED LIST (Activity Log)
    // ════════════════════════════════════════════════════════

    // Node class for singly linked list
    static class ActivityNode {
        String activity;
        String type; // "workout" or "meal"
        ActivityNode next;
        ActivityNode(String activity, String type) {
            this.activity = activity; this.type = type; this.next = null;
        }
    }

    static ActivityNode llHead = null;
    static int llSize = 0;

    static void co2Menu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  ── CO2: Singly Linked List ─────────────");
            System.out.println("  [1] Insert at Head    O(1)");
            System.out.println("  [2] Insert at Tail    O(n)");
            System.out.println("  [3] Delete at Head    O(1)");
            System.out.println("  [4] Delete by value   O(n)");
            System.out.println("  [5] Search activity   O(n)");
            System.out.println("  [6] Traverse / Print  O(n)");
            System.out.println("  [7] Reverse list      O(n)");
            System.out.println("  [8] Detect cycle      O(n) Floyd's");
            System.out.println("  [9] List length       O(n)");
            System.out.println("  [0] Back");
            int ch = readInt("  > ");
            switch (ch) {
                case 1 -> llInsertHead();
                case 2 -> llInsertTail();
                case 3 -> llDeleteHead();
                case 4 -> llDeleteByValue();
                case 5 -> llSearch();
                case 6 -> llTraverse();
                case 7 -> llReverse();
                case 8 -> llDetectCycle();
                case 9 -> System.out.println("  📏 List length: " + llSize + " — O(n) traversal");
                case 0 -> back = true;
                default -> System.out.println("  ❌ Invalid.");
            }
        }
    }

    // Insert at HEAD — O(1): no traversal needed
    static void llInsertHead() {
        System.out.print("  Activity name: "); String a = sc.nextLine().trim();
        System.out.print("  Type (workout/meal): "); String t = sc.nextLine().trim();
        ActivityNode node = new ActivityNode(a, t);
        node.next = llHead;
        llHead = node;
        llSize++;
        System.out.println("  ✅ Inserted at HEAD. O(1) — no traversal. Size: " + llSize);
    }

    // Insert at TAIL — O(n): must traverse to last node
    static void llInsertTail() {
        System.out.print("  Activity name: "); String a = sc.nextLine().trim();
        System.out.print("  Type (workout/meal): "); String t = sc.nextLine().trim();
        ActivityNode node = new ActivityNode(a, t);
        if (llHead == null) { llHead = node; llSize++; System.out.println("  ✅ Inserted as first node. Size: 1"); return; }
        ActivityNode curr = llHead;
        int steps = 1;
        while (curr.next != null) { curr = curr.next; steps++; }
        curr.next = node;
        llSize++;
        System.out.println("  ✅ Inserted at TAIL after traversing " + steps + " node(s). O(n). Size: " + llSize);
    }

    // Delete HEAD — O(1)
    static void llDeleteHead() {
        if (llHead == null) { System.out.println("  ⚠️ List is empty."); return; }
        System.out.println("  🗑 Deleted HEAD: [" + llHead.activity + "] — O(1)");
        llHead = llHead.next;
        llSize--;
    }

    // Delete by value — O(n)
    static void llDeleteByValue() {
        if (llHead == null) { System.out.println("  ⚠️ List is empty."); return; }
        System.out.print("  Activity to delete: "); String target = sc.nextLine().trim();
        if (llHead.activity.equalsIgnoreCase(target)) { llHead = llHead.next; llSize--; System.out.println("  ✅ Deleted HEAD match."); return; }
        ActivityNode prev = llHead, curr = llHead.next; int steps = 1;
        while (curr != null) {
            steps++;
            if (curr.activity.equalsIgnoreCase(target)) {
                prev.next = curr.next; llSize--;
                System.out.println("  ✅ Deleted \"" + target + "\" found after " + steps + " step(s). O(n).");
                return;
            }
            prev = curr; curr = curr.next;
        }
        System.out.println("  ❌ \"" + target + "\" not found after " + steps + " step(s).");
    }

    // Search — O(n)
    static void llSearch() {
        System.out.print("  Search activity: "); String target = sc.nextLine().trim();
        ActivityNode curr = llHead; int idx = 0;
        while (curr != null) {
            idx++;
            if (curr.activity.equalsIgnoreCase(target)) {
                System.out.println("  ✅ Found \"" + target + "\" at position " + idx + " in " + idx + " step(s). O(n).");
                return;
            }
            curr = curr.next;
        }
        System.out.println("  ❌ Not found after " + idx + " step(s). O(n).");
    }

    // Traverse — O(n)
    static void llTraverse() {
        if (llHead == null) { System.out.println("  ⚠️ List is empty."); return; }
        System.out.println("  HEAD → ");
        ActivityNode curr = llHead; int i = 1;
        while (curr != null) {
            System.out.printf("  [%d] %-20s (%s)%s%n", i++, curr.activity, curr.type, curr.next == null ? " ← TAIL" : "");
            curr = curr.next;
        }
        System.out.println("  NULL");
        System.out.println("  📏 Size: " + llSize + " | Traversal: O(n)");
    }

    // Reverse — O(n): flip all next pointers
    static void llReverse() {
        if (llHead == null) { System.out.println("  ⚠️ List is empty."); return; }
        ActivityNode prev = null, curr = llHead, next;
        int steps = 0;
        while (curr != null) { next = curr.next; curr.next = prev; prev = curr; curr = next; steps++; }
        llHead = prev;
        System.out.println("  🔁 Reversed in " + steps + " step(s). O(n) — each node's next pointer flipped.");
        llTraverse();
    }

    // Floyd's Cycle Detection — O(n) time, O(1) space
    static void llDetectCycle() {
        System.out.println("  🔍 Floyd's Two-Pointer Cycle Detection: O(n) time, O(1) space");
        if (llHead == null) { System.out.println("  List empty — no cycle."); return; }
        ActivityNode slow = llHead, fast = llHead;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { System.out.println("  ⚠️ Cycle DETECTED! (slow == fast pointer)"); return; }
        }
        System.out.println("  ✅ No cycle detected. List terminates at NULL.");
    }

    // ════════════════════════════════════════════════════════
    //  CO3 — STACK, QUEUE, MAX-HEAP
    // ════════════════════════════════════════════════════════

    // Stack — array-based LIFO
    static String[] stackArr = new String[20];
    static int stackTop = -1;

    // Circular Queue — array-based FIFO
    static String[] queueArr = new String[10];
    static int qFront = 0, qRear = 0, qCount = 0;
    static final int Q_CAP = 10;

    // Max-Heap — array-based priority queue
    static int[] heapArr = new int[20];
    static int heapSize = 0;

    static void co3Menu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n  ── CO3: Stack / Queue / Heap ───────────");
            System.out.println("  STACK (Workout Undo History)");
            System.out.println("  [1] Push workout     O(1)");
            System.out.println("  [2] Pop workout      O(1)");
            System.out.println("  [3] Peek top         O(1)");
            System.out.println("  [4] Print stack");
            System.out.println("  CIRCULAR QUEUE (Meal Schedule)");
            System.out.println("  [5] Enqueue meal     O(1)");
            System.out.println("  [6] Dequeue meal     O(1)");
            System.out.println("  [7] Print queue");
            System.out.println("  MAX-HEAP (Priority Queue)");
            System.out.println("  [8] Insert priority  O(log n)");
            System.out.println("  [9] Extract max      O(log n)");
            System.out.println("  [A] Print heap");
            System.out.println("  [0] Back");
            int ch = readInt("  > ");
            switch (ch) {
                case 1 -> stackPush();
                case 2 -> stackPop();
                case 3 -> stackPeek();
                case 4 -> stackPrint();
                case 5 -> queueEnqueue();
                case 6 -> queueDequeue();
                case 7 -> queuePrint();
                case 8 -> heapInsert();
                case 9 -> heapExtractMax();
                case 10 -> heapPrint();
                case 0 -> back = true;
                default -> System.out.println("  ❌ Invalid. (Enter 10 for 'A')");
            }
        }
    }

    // ── STACK LIFO ───────────────────────────────────────────
    static void stackPush() {
        if (stackTop == stackArr.length - 1) { System.out.println("  ⚠️ Stack overflow!"); return; }
        System.out.print("  Workout to push: "); String v = sc.nextLine().trim();
        stackArr[++stackTop] = v;
        System.out.println("  ✅ Pushed \"" + v + "\" → TOP. O(1). Stack size: " + (stackTop+1));
    }

    static void stackPop() {
        if (stackTop == -1) { System.out.println("  ⚠️ Stack underflow — empty!"); return; }
        String v = stackArr[stackTop--];
        System.out.println("  🗑 Popped \"" + v + "\" from TOP. O(1). Stack size: " + (stackTop+1));
    }

    static void stackPeek() {
        if (stackTop == -1) { System.out.println("  ⚠️ Stack is empty."); return; }
        System.out.println("  👀 Peek → TOP = \"" + stackArr[stackTop] + "\". O(1) — no removal.");
    }

    static void stackPrint() {
        if (stackTop == -1) { System.out.println("  Stack is empty."); return; }
        System.out.println("  ┌─── STACK (LIFO) ───┐");
        for (int i = stackTop; i >= 0; i--)
            System.out.printf("  │  %-18s │%s%n", stackArr[i], i == stackTop ? " ← TOP" : "");
        System.out.println("  └────────────────────┘");
    }

    // ── CIRCULAR QUEUE FIFO ──────────────────────────────────
    // Uses modulo arithmetic to wrap around — avoids O(n) shifting
    static void queueEnqueue() {
        if (qCount == Q_CAP) { System.out.println("  ⚠️ Queue is full!"); return; }
        System.out.print("  Meal to enqueue: "); String v = sc.nextLine().trim();
        queueArr[qRear] = v;
        qRear = (qRear + 1) % Q_CAP; // circular wrap: O(1)
        qCount++;
        System.out.println("  ✅ Enqueued \"" + v + "\" at REAR. O(1). Queue size: " + qCount);
    }

    static void queueDequeue() {
        if (qCount == 0) { System.out.println("  ⚠️ Queue is empty!"); return; }
        String v = queueArr[qFront];
        qFront = (qFront + 1) % Q_CAP; // circular wrap
        qCount--;
        System.out.println("  🗑 Dequeued \"" + v + "\" from FRONT. O(1). Queue size: " + qCount);
    }

    static void queuePrint() {
        if (qCount == 0) { System.out.println("  Queue is empty."); return; }
        System.out.println("  ┌─── CIRCULAR QUEUE (FIFO) ─────────────┐");
        System.out.print("  │  FRONT → ");
        for (int i = 0; i < qCount; i++) {
            int idx = (qFront + i) % Q_CAP;
            System.out.print("[" + queueArr[idx] + "]");
            if (i < qCount - 1) System.out.print(" → ");
        }
        System.out.println(" ← REAR");
        System.out.println("  └────────────────────────────────────────┘");
        System.out.println("  Capacity: " + Q_CAP + " | Used: " + qCount + " | Circular avoids O(n) shift.");
    }

    // ── MAX-HEAP PRIORITY QUEUE ───────────────────────────────
    // Insert: sift up O(log n) | Extract: sift down O(log n)
    // Parent of i = (i-1)/2 | Left child = 2i+1 | Right child = 2i+2
    static void heapInsert() {
        if (heapSize == heapArr.length) { System.out.println("  ⚠️ Heap full!"); return; }
        int v = readInt("  Enter workout priority (1-99): ");
        heapArr[heapSize] = v;
        int i = heapSize++;
        int swaps = 0;
        // Sift up: compare with parent, swap if greater
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heapArr[parent] < heapArr[i]) {
                int tmp = heapArr[parent]; heapArr[parent] = heapArr[i]; heapArr[i] = tmp;
                i = parent; swaps++;
            } else break;
        }
        System.out.printf("  ✅ Inserted %d — sifted up %d swap(s). O(log n). Max = %d%n", v, swaps, heapArr[0]);
    }

    static void heapExtractMax() {
        if (heapSize == 0) { System.out.println("  ⚠️ Heap is empty!"); return; }
        int max = heapArr[0];
        heapArr[0] = heapArr[--heapSize];
        // Sift down: restore heap property
        int i = 0, swaps = 0;
        while (true) {
            int largest = i, l = 2*i+1, r = 2*i+2;
            if (l < heapSize && heapArr[l] > heapArr[largest]) largest = l;
            if (r < heapSize && heapArr[r] > heapArr[largest]) largest = r;
            if (largest != i) {
                int tmp = heapArr[i]; heapArr[i] = heapArr[largest]; heapArr[largest] = tmp;
                i = largest; swaps++;
            } else break;
        }
        System.out.printf("  🔥 Extracted MAX = %d — sifted down %d swap(s). O(log n). New max = %s%n",
                max, swaps, heapSize > 0 ? String.valueOf(heapArr[0]) : "—");
    }

    static void heapPrint() {
        if (heapSize == 0) { System.out.println("  Heap is empty."); return; }
        System.out.println("  ┌─── MAX-HEAP (array form) ──┐");
        System.out.print("  │  ");
        for (int i = 0; i < heapSize; i++) System.out.print((i==0?"MAX:":"")+heapArr[i]+(i<heapSize-1?" > ":""));
        System.out.println();
        System.out.println("  ├─── Tree View ──────────────┤");
        // Print first 3 levels of heap tree
        int level = 0, count = 1;
        int printed = 0;
        while (printed < heapSize && level < 4) {
            System.out.print("  │  L" + level + ": ");
            for (int i = 0; i < count && printed < heapSize; i++, printed++)
                System.out.print("[" + heapArr[printed] + "] ");
            System.out.println();
            count *= 2; level++;
        }
        System.out.println("  └────────────────────────────┘");
        System.out.println("  Parent(i)=(i-1)/2 | Left=2i+1 | Right=2i+2");
    }

    // ════════════════════════════════════════════════════════
    //  CO4 — HASH MAP (Meal Calorie Lookup)
    // ════════════════════════════════════════════════════════

    // Custom chaining hash table
    static final int HT_SIZE = 16;

    static class HTEntry {
        String key;
        int calories;
        HTEntry next;
        HTEntry(String key, int calories) { this.key = key; this.calories = calories; }
    }

    static HTEntry[] hashTable = new HTEntry[HT_SIZE];

    // Java built-in HashMap for comparison
    static HashMap<String, Integer> javaMap = new HashMap<>();
    static LinkedList<String> recentLookups = new LinkedList<>(); // Deque usage
    static PriorityQueue<int[]> topMeals = new PriorityQueue<>((a,b) -> b[0]-a[0]); // Max PQ

    static void co4Menu() {
        boolean back = false;
        // Pre-load some meals
        if (hashTable[hashFn("Oats")] == null) {
            htInsert("Oats", 350); htInsert("Chicken", 520); htInsert("Rice", 400);
            htInsert("Salad", 180); htInsert("Pasta", 600); htInsert("Eggs", 300);
            htInsert("Banana", 90); htInsert("Milk", 150);
            javaMap.put("Oats",350); javaMap.put("Chicken",520); javaMap.put("Rice",400);
            javaMap.put("Salad",180); javaMap.put("Pasta",600); javaMap.put("Eggs",300);
            System.out.println("  ✅ Pre-loaded 8 meals into hash table.");
        }
        while (!back) {
            System.out.println("\n  ── CO4: Hash Table & Collections ───────");
            System.out.println("  Custom Chaining Hash Table:");
            System.out.println("  [1] Insert meal → calories     O(1) avg");
            System.out.println("  [2] Search meal calorie        O(1) avg");
            System.out.println("  [3] Delete meal                O(1) avg");
            System.out.println("  [4] Print all buckets");
            System.out.println("  [5] Show load factor & stats");
            System.out.println("  Java Collections:");
            System.out.println("  [6] Java HashMap put/get       O(1) avg");
            System.out.println("  [7] Show recent lookups (Deque/LinkedList)");
            System.out.println("  [8] Top-calorie meals (PriorityQueue)");
            System.out.println("  [9] Collision demo (chaining)");
            System.out.println("  [0] Back");
            int ch = readInt("  > ");
            switch (ch) {
                case 1 -> htInsertMenu();
                case 2 -> htSearchMenu();
                case 3 -> htDeleteMenu();
                case 4 -> htPrintAll();
                case 5 -> htStats();
                case 6 -> javaHashMapDemo();
                case 7 -> printRecentLookups();
                case 8 -> topMealsDemo();
                case 9 -> collisionDemo();
                case 0 -> back = true;
                default -> System.out.println("  ❌ Invalid.");
            }
        }
    }

    // Hash function: sum of char codes % HT_SIZE
    static int hashFn(String key) {
        int sum = 0;
        for (char c : key.toCharArray()) sum += c;
        return sum % HT_SIZE;
    }

    static void htInsert(String key, int val) {
        int idx = hashFn(key);
        HTEntry curr = hashTable[idx];
        while (curr != null) { if (curr.key.equalsIgnoreCase(key)) { curr.calories = val; return; } curr = curr.next; }
        HTEntry node = new HTEntry(key, val);
        node.next = hashTable[idx];
        hashTable[idx] = node;
        javaMap.put(key, val);
        topMeals.offer(new int[]{val, key.hashCode()});
    }

    static void htInsertMenu() {
        System.out.print("  Meal name: "); String k = sc.nextLine().trim();
        int v = readInt("  Calories: ");
        int idx = hashFn(k);
        int chainLen = 0;
        HTEntry curr = hashTable[idx];
        while (curr != null) { chainLen++; curr = curr.next; }
        htInsert(k, v);
        System.out.printf("  ✅ Inserted [%s → %d kcal] at bucket[%d]. hashFn(\"%s\")=%d.%s%n",
                k, v, idx, k, idx, chainLen > 0 ? " ⚡ Collision! Chain length: " + (chainLen+1) : " No collision.");
    }

    static void htSearchMenu() {
        System.out.print("  Meal to search: "); String k = sc.nextLine().trim();
        int idx = hashFn(k);
        HTEntry curr = hashTable[idx]; int steps = 0;
        while (curr != null) {
            steps++;
            if (curr.key.equalsIgnoreCase(k)) {
                System.out.printf("  ✅ Found [%s → %d kcal] at bucket[%d] in %d step(s). O(1) avg.%n", k, curr.calories, idx, steps);
                recentLookups.addFirst(k + ":" + curr.calories + " kcal");
                if (recentLookups.size() > 5) recentLookups.removeLast();
                return;
            }
            curr = curr.next;
        }
        System.out.printf("  ❌ \"%s\" not found at bucket[%d] after %d step(s).%n", k, idx, steps);
    }

    static void htDeleteMenu() {
        System.out.print("  Meal to delete: "); String k = sc.nextLine().trim();
        int idx = hashFn(k);
        if (hashTable[idx] == null) { System.out.println("  ❌ Bucket empty. Not found."); return; }
        if (hashTable[idx].key.equalsIgnoreCase(k)) {
            hashTable[idx] = hashTable[idx].next;
            System.out.println("  ✅ Deleted \"" + k + "\" from bucket[" + idx + "]. O(1)."); return;
        }
        HTEntry prev = hashTable[idx], curr = prev.next;
        while (curr != null) {
            if (curr.key.equalsIgnoreCase(k)) { prev.next = curr.next; System.out.println("  ✅ Deleted \"" + k + "\" from chain at bucket[" + idx + "]."); return; }
            prev = curr; curr = curr.next;
        }
        System.out.println("  ❌ \"" + k + "\" not found.");
    }

    static void htPrintAll() {
        System.out.println("  ┌─── Hash Table (size=" + HT_SIZE + ") ────────────────┐");
        int total = 0;
        for (int i = 0; i < HT_SIZE; i++) {
            if (hashTable[i] != null) {
                System.out.printf("  │  [%02d] → ", i);
                HTEntry curr = hashTable[i]; int chain = 0;
                while (curr != null) {
                    System.out.print(curr.key + ":" + curr.calories + (curr.next != null ? " → " : ""));
                    curr = curr.next; total++; chain++;
                }
                if (chain > 1) System.out.print("  (chain=" + chain + ")");
                System.out.println();
            }
        }
        System.out.println("  └──────────────────────────────────────────┘");
        System.out.printf("  Total entries: %d | Load factor: %.2f%n", total, (double)total/HT_SIZE);
    }

    static void htStats() {
        int total = 0, buckets = 0, maxChain = 0, collisions = 0;
        for (int i = 0; i < HT_SIZE; i++) {
            if (hashTable[i] != null) {
                buckets++; int chain = 0;
                HTEntry curr = hashTable[i];
                while (curr != null) { total++; chain++; curr = curr.next; }
                if (chain > 1) collisions += chain - 1;
                maxChain = Math.max(maxChain, chain);
            }
        }
        double lf = (double)total / HT_SIZE;
        System.out.println("  ┌─── Hash Table Statistics ────────────┐");
        System.out.printf ("  │  Table size      : %d buckets         │%n", HT_SIZE);
        System.out.printf ("  │  Total entries   : %d                 │%n", total);
        System.out.printf ("  │  Occupied buckets: %d                 │%n", buckets);
        System.out.printf ("  │  Load factor     : %.2f               │%n", lf);
        System.out.printf ("  │  Collisions      : %d                 │%n", collisions);
        System.out.printf ("  │  Max chain length: %d                 │%n", maxChain);
        System.out.printf ("  │  Avg lookup O(1+%.2f) ≈ O(1) avg     │%n", lf);
        System.out.println("  │  Rehash threshold: 0.75 (Java default)│");
        System.out.println("  └───────────────────────────────────────┘");
        if (lf > 0.75) System.out.println("  ⚠️  Load factor > 0.75 — consider rehashing!");
    }

    static void javaHashMapDemo() {
        System.out.println("\n  ── Java HashMap<String,Integer> Demo ───");
        System.out.print("  Key (meal): "); String k = sc.nextLine().trim();
        int v = readInt("  Value (calories): ");
        javaMap.put(k, v); // O(1) avg
        System.out.println("  javaMap.put(\"" + k + "\", " + v + ") → O(1) avg");
        System.out.println("  javaMap.get(\"" + k + "\") → " + javaMap.get(k)); // O(1) avg
        System.out.println("  javaMap.containsKey(\"" + k + "\") → " + javaMap.containsKey(k));
        System.out.println("  javaMap.size() → " + javaMap.size());
        System.out.println("\n  Full map: " + javaMap);
    }

    static void printRecentLookups() {
        System.out.println("  ── Recent Lookups (LinkedList as Deque) ─");
        System.out.println("  addFirst() = O(1) | removeLast() = O(1)");
        if (recentLookups.isEmpty()) { System.out.println("  No lookups yet. Search meals first."); return; }
        System.out.println("  ┌── Most Recent ──────────────┐");
        int i = 1;
        for (String s : recentLookups)
            System.out.printf("  │  %d. %-24s │%n", i++, s);
        System.out.println("  └─────────────────────────────┘");
    }

    static void topMealsDemo() {
        // Re-build priority queue from Java HashMap
        PriorityQueue<Map.Entry<String,Integer>> pq =
            new PriorityQueue<>((a,b) -> b.getValue()-a.getValue());
        pq.addAll(javaMap.entrySet());
        System.out.println("  ── Top Calorie Meals (PriorityQueue Max-Heap) ──");
        System.out.println("  Insert O(log n) | Poll O(log n) | Peek O(1)");
        System.out.println("  ┌─────────────────────────────────────┐");
        int rank = 1;
        while (!pq.isEmpty() && rank <= 5) {
            Map.Entry<String,Integer> e = pq.poll();
            System.out.printf("  │  #%d  %-15s → %4d kcal  │%n", rank++, e.getKey(), e.getValue());
        }
        System.out.println("  └─────────────────────────────────────┘");
    }

    static void collisionDemo() {
        System.out.println("  ── Collision Demo (Chaining) ────────────");
        System.out.println("  Inserting keys that hash to same bucket...");
        String[] keys = {"Oats","toAs","sAot"}; // all sum to same hash
        for (String k : keys) {
            int idx = hashFn(k);
            System.out.printf("  hashFn(\"%s\") = %d → bucket[%d]%n", k, idx, idx);
        }
        System.out.println("  Chaining: collisions stored as linked list in bucket.");
        System.out.println("  Lookup still O(1) avg when load factor is low.");
        System.out.println("  Worst case: all keys to one bucket → O(n).");
    }

    // ════════════════════════════════════════════════════════
    //  UTILITY
    // ════════════════════════════════════════════════════════
    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("  ❌ Enter a valid number."); }
        }
    }
}