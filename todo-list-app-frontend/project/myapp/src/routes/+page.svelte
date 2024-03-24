<h1>To-Do List</h1>
<script lang="ts">
  import { onMount } from 'svelte';
  import { writable } from 'svelte/store';

  // Define a writable store to hold todo items
  const todos = writable([]);

  // Function to fetch todo items from the API
  async function fetchTodos() {
    try {
      const response = await fetch('http://localhost:8080/v1/items/');
      const data = await response.json();
      todos.set(data); // Update the todos store with fetched data
    } catch (error) {
      console.error('Error fetching todos:', error);
    }
  }

  // Fetch todos when the component is mounted
  onMount(fetchTodos);

</script>

<ul>
  {#each $todos as todo}
    <li>{todo.description}</li>
    <input type="checkbox" bind:checked={todo.isDone}>
  {/each}
</ul>

