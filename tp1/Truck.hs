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
                                            | (freeCellsS pila > 0
                                              && netS pila + netP palet <= 10
                                              && holdsS pila palet ruta )
                                              = i
                                            | otherwise = findFreeStackT (Tru bahias ruta) palet (i + 1)
                                                where
                                                    pila = bahias !! i


changeStackT :: Int -> Stack -> [Stack] -> [Stack]
changeStackT _ _ [] = []
changeStackT 0 pila (_:ps) = pila:ps
changeStackT i pila (p:ps) = p:changeStackT (i-1) pila ps


loadT :: Truck -> Palet -> Truck      -- carga un palet en el camion
loadT (Tru bahias ruta) palet   | indice == -1 = error "No se pudo cargar el palet, no hay una bahia compatible disponible."
                                | otherwise = Tru (changeStackT indice (stackS (bahias !! indice) palet) bahias) ruta
                                where indice = findFreeStackT (Tru bahias ruta) palet 0
-- Decidimos atrapar el error en esta funcion y hacer que findFreeStackT retorne -1 para facilitar el entendimiento del codigo y las funciones

unloadT :: Truck -> String -> Truck   -- responde un camion al que se le han descargado los paletes que podían descargarse en la ciudad
unloadT (Tru bahias ruta) ciudad = Tru (map (\stack -> popS stack ciudad) bahias) ruta


netT :: Truck -> Int                  -- responde el peso neto en toneladas de los paletes en el camion
netT (Tru bahias _) = foldr (+) 0 (map netS bahias)