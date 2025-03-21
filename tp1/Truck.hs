module Truck ( Truck, newT, freeCellsT, loadT, unloadT, netT )
 where

import Palet
import Stack
import Route


data Truck = Tru [ Stack ] Route deriving (Eq, Show)

newT :: Int -> Int -> Route -> Truck  -- construye un camion según una cantidad de bahias, la altura de las mismas y una ruta
newT bahias altura ruta = Tru [newS altura | _ <- [1..bahias]] ruta


freeCellsT :: Truck -> Int            -- responde la celdas disponibles en el camion
freeCellsT (Tru bahias _) = foldr (+) 0 [freeCellsS pila | pila <- bahias]


findFreeStackT :: Truck -> Palet -> Int -> Int
findFreeStackT (Tru bahias ruta) palet i    | length bahias <= i = -1
                                       | freeCellsS (bahias !! i) > 0
                                       && (netS (bahias !! i)) + (netP palet) <= 10 
                                       && holdsS (bahias !! i) palet ruta 
                                       = i
                                       | otherwise = findFreeStackT (Tru bahias ruta) palet (i+1)


changeStackT :: Int -> Stack -> [Stack] -> [Stack]
changeStackT _ _ [] = []
changeStackT 0 pila (_:ps) = pila:ps
changeStackT i pila (p:ps) = p:changeStackT (i-1) pila ps


loadT :: Truck -> Palet -> Truck      -- carga un palet en el camion
loadT (Tru bahias ruta) palet   | indice == -1 = error "No se pudo cargar el palet, no hay una bahia compatible disponible."
                                | otherwise = Tru (changeStackT indice (stackS (bahias !! indice) palet) bahias) ruta
                                where indice = findFreeStackT (Tru bahias ruta) palet 0
-- Decidimos atrapar el error en esta funcion y hacer que findFreeStackT retorne -1 para facilitar el entendimiento del codigo y las funciones
                            

-- que haya lugares libres
-- que no supere el peso (netS)
-- que pueda admitir el palet considerando la ruta (holdsS)

--removePaletT :: Stack -> String -> Stack
--removePaletT (Sta [] capacidad) _ = (Sta [] capacidad)
--removePaletT (Sta (p:ps) capacidad) ciudad  | destinationP p == ciudad = removePaletT (Sta ps capacidad) ciudad
--                                            | otherwise = (Sta (p:ps) capacidad)


unloadT :: Truck -> String -> Truck   -- responde un camion al que se le han descargado los paletes que podían descargarse en la ciudad
unloadT (Tru bahias ruta) ciudad = Tru (map (\stack -> popS stack ciudad) bahias) ruta


netT :: Truck -> Int                  -- responde el peso neto en toneladas de los paletes en el camion
netT (Tru bahias _) = foldr (+) 0 (map netS bahias)




p = newP "bsas" 4
p2 = newP "tigre" 8
p3 = newP "belgrano" 2
p4 = newP "lujan" 1
p5 = newP "bsas" 2
s = newS 3

r = newR ["bsas", "lujan", "tigre", "CABA", "tucuman"]
t = newT 1 3 r

s2 = stackS s p2
s3 = stackS s2 p
s4 = newS 3

t2 = loadT t p
t3 = loadT t2 p2
t4 = loadT t3 p4

t5 = unloadT t4 "bsas"