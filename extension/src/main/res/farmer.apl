// An example of a "farmer" agent
agent(Farmer)
{
    knowledge { }

    // Prior beliefs of a farmer
    beliefs
    {
      not leave or not (improve or enlarge)
    }

    desires
    {
      // general rules, to cover most cases:
      if B(viable) then not leave,
      if B(not viable) and 0.25 then leave,
      if B(fertile) and 0.5 then enlarge,
      if B(accessible) and 0.25 then enlarge,

      // specific rules:
      if B(not accessible and not fertile and not viable) then leave,
      if B(not fertile and not viable) and 0.5 then leave,
      if B(not accessible and fertile and not viable) then improve,
      if B(not accessible and fertile and not viable) and 0.5 then enlarge,
      if B(fertile and ((not accessible and viable) or (accessible and not viable)))
      then enlarge,
      if B(accessible and fertile and not viable) and 0.5 then improve,
      if B(accessible and not fertile and viable) and 0.25 then enlarge,
      if B(accessible and fertile and viable) and 0.75 then enlarge
    }

    obligations { }
}
