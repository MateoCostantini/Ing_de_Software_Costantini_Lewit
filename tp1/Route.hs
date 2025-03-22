
module Route ( Route, newR, inOrderR, inRouteR)
  where

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR cities = Rou cities
-- Como hacer para que si o si sea una lista lo que reciba

-- inOrderR :: Route -> String -> String -> Bool  -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta
-- inOrderR (Rou []) _ _ = error "La ciudad consultada no esta en la ruta."  
-- inOrderR (Rou (c:cs)) cityQuery citySearch  | c == cityQuery = True
--                                             | c == citySearch = False
--                                             | otherwise = inOrderR (Rou cs) cityQuery citySearch 

inOrderR :: Route -> String -> String -> Bool  
inOrderR (Rou cities) cityQuery citySearch
    | null cities               = error "La ruta está vacía."
    | cityQuery == citySearch   = True
    | not (elem cityQuery cities) = False
    | not (elem citySearch cities) = True
    | otherwise = firstOccurrence cities cityQuery < lastOccurrence cities citySearch
  where
    -- Devuelve el índice de la primera ocurrencia de la ciudad.
    firstOccurrence :: [String] -> String -> Int
    firstOccurrence [] _ = error "La ciudad no está en la ruta."
    firstOccurrence (c:cs) city
         | c == city = 0
         | otherwise = 1 + firstOccurrence cs city

    -- Devuelve el índice de la última ocurrencia de la ciudad.
    lastOccurrence :: [String] -> String -> Int
    lastOccurrence cities city = lastOccurrenceAux cities city 0 (-1)
      where
         lastOccurrenceAux [] _ _ current = current
         lastOccurrenceAux (c:cs) city idx current
              | c == city = lastOccurrenceAux cs city (idx+1) idx
              | otherwise = lastOccurrenceAux cs city (idx+1) current


inRouteR :: Route -> String -> Bool -- indica si la ciudad consultada está en la ruta
inRouteR (Rou []) _ = False
inRouteR (Rou (c:cs)) cityQuery | c == cityQuery = True
                                | otherwise = inRouteR (Rou cs) cityQuery