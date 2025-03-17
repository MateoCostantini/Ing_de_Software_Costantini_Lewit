
module Route ( Route, newR, inOrderR, inRouteR)
  where

data Route = Rou [ String ] deriving (Eq, Show)

newR :: [ String ] -> Route                    -- construye una ruta segun una lista de ciudades
newR cities = Rou cities
-- Como hacer para que si o si sea una lista lo que reciba

inOrderR :: Route -> String -> String -> Bool  -- indica si la primer ciudad consultada esta antes que la segunda ciudad en la ruta
inOrderR (Rou []) _ _ = False  
inOrderR (Rou (c:cs)) cityQuery citySearch  | c == cityQuery = True
                                              | c == citySearch = False
                                              | otherwise = inOrderR (Rou cs) cityQuery citySearch 


inRouteR :: Route -> String -> Bool -- indica si la ciudad consultada está en la ruta
inRouteR (Rou []) _ = False
inRouteR (Rou (c:cs)) cityQuery | c == cityQuery = True
                                | otherwise = inRouteR (Rou cs) cityQuery