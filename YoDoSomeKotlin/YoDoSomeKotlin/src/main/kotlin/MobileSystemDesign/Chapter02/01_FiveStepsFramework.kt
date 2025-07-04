package MobileSystemDesign.Chapter02

/**
 *  Five Steps Framework
 *      1.  Understanding the problem and establish design scope
 *      2.  API Design
 *      3.  High level client architecture
 *      4.  Design deep dive
 *      5.  Wrap up
 *
 *
 *      One by one:
 *
 *      1.  Understand the problem and establish design scope: 5 - 10 mins
 *          *   Start with straightforward questions like design ... app
 *          *   Its important to ask clarifying questions about scope, technology choices and make tradeoffs.
 *          *   Questions that you should ask:
 *              A.  What are we building? - clarify functional and non func requirements. Also what is out of scope.
 *              B.  Who are the customers? - To understand scale, usage patterns. Ask questions like DAU?, can it grow?
 *                                          Are we building MVP? , Target market? worldwide or regional?
 *              C.  What are systems constraints? - What platform? iOS or Android..
 *
 *          Reference back to these requirements when explaining your choices.
 *
 *       2. API Design: 5 - 10 mins
 *          *   Helps establish contract between app and outside systems.
 *          *   Start by determining the communication protocol
 *          *   Outline json payloads, data classes, daos and pojos.
 *
 *       3. High level Design: 10 - 15 mins
 *          *   A high level sketch of app architecture.
 *
 *       4. Design Deep Dive: 15 - 20 mins
 *          *   Explore specific components in detail.
 *          *   Expect 1-3 deep dives
 *          *   Refine your architecture
 *          *   Keep updating diagram following design decisions, tradeoffs and addition of new components.
 *          *   Pay attention to interviewer. Constraint various deep discussions within interview time limits.
 *
 *      5.  Wrap Up: If time permits 0 - 5 mins
 *          *   Summarize critical decisions
 *          *   Discuss potential improvements. No design is perfect.
 *          *   Address edge cases. Exceptions, failure points...
 *          *   Discuss future scaling - how system will handle growth in future.
 *
 *
 */